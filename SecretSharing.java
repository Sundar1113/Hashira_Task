import java.math.BigInteger;
import java.util.*;
import java.util.regex.*;

public class SecretSharing {
    static class Share { int x; BigInteger y; Share(int x, BigInteger y){this.x=x;this.y=y;} }

    static BigInteger lagrange(List<Share> s) {
        BigInteger num = BigInteger.ZERO, den = BigInteger.ONE;
        for (int i=0;i<s.size();i++) {
            BigInteger tNum = s.get(i).y, tDen = BigInteger.ONE;
            for (int j=0;j<s.size();j++) if (i!=j) {
                tNum = tNum.multiply(BigInteger.valueOf(-s.get(j).x));
                tDen = tDen.multiply(BigInteger.valueOf(s.get(i).x - s.get(j).x));
            }
            num = num.multiply(tDen).add(tNum.multiply(den));
            den = den.multiply(tDen);
            BigInteger g=num.gcd(den); num=num.divide(g); den=den.divide(g);
        }
        return num.divide(den);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while(sc.hasNextLine()) sb.append(sc.nextLine());
        String json = sb.toString();

        Matcher mk = Pattern.compile("\"n\"\\s*:\\s*(\\d+).*?\"k\"\\s*:\\s*(\\d+)", Pattern.DOTALL).matcher(json);
        mk.find();
        int n = Integer.parseInt(mk.group(1));
        int k = Integer.parseInt(mk.group(2));

        Matcher ms = Pattern.compile("\"(\\d+)\"\\s*:\\s*\\{\\s*\"base\"\\s*:\\s*\"(\\d+)\"\\s*,\\s*\"value\"\\s*:\\s*\"([^\"]+)\"").matcher(json);
        Map<Integer,Share> shares = new LinkedHashMap<>();
        while(ms.find()){
            int x = Integer.parseInt(ms.group(1));
            int base = Integer.parseInt(ms.group(2));
            String val = ms.group(3);
            shares.put(x, new Share(x,new BigInteger(val, base)));
        }

        List<Share> arr = new ArrayList<>(shares.values());
        Map<BigInteger,List<Integer>> freq = new HashMap<>();
        comb(arr,k,0,new ArrayList<>(),freq);
        BigInteger secret = freq.entrySet().stream()
            .max(Map.Entry.comparingByValue(Comparator.comparingInt(List::size)))
            .get().getKey();
        System.out.println("Secret: " + secret);

        Set<Integer> good = new HashSet<>(freq.get(secret));
        List<Integer> wrong = new ArrayList<>();
        for(int x: shares.keySet()) if(!good.contains(x)) wrong.add(x);
        System.out.println("Wrong shares: " + wrong);
    }

    static void comb(List<Share> arr,int k,int idx,List<Share> cur,Map<BigInteger,List<Integer>> freq){
        if(cur.size()==k){
            BigInteger s=lagrange(cur);
            freq.computeIfAbsent(s,z->new ArrayList<>()).addAll(cur.stream().map(ss->ss.x).toList());
            return;
        }
        if(idx==arr.size()) return;
        cur.add(arr.get(idx)); comb(arr,k,idx+1,cur,freq); cur.remove(cur.size()-1);
        comb(arr,k,idx+1,cur,freq);
    }
}
