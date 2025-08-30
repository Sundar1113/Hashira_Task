# Shamir Secret Sharing Reconstruction

A Java implementation of **Shamir's Secret Sharing (SSS)** scheme. This program reconstructs the secret from shares and identifies any incorrect or malicious shares. It handles large numbers and supports shares in multiple numeric bases.

---

## Features

- Accepts user input in **JSON format** containing `n` shares and minimum `k` shares required to reconstruct the secret.
- Supports shares in different numeric bases (binary, decimal, octal, hexadecimal, etc.).
- Computes the secret (constant term of the polynomial) using **Lagrange interpolation** at x = 0.
- Automatically detects incorrect or malicious shares.
- Handles very large numbers using `BigInteger`.
- Lightweight and easy-to-read Java code.

---

## Input Format

Input should be in **JSON format**. Example:

json
{
  "keys": {
    "n": 4,
    "k": 3
  },
  "1": { "base": "10", "value": "4" },
  "2": { "base": "2", "value": "111" },
  "3": { "base": "10", "value": "12" },
  "6": { "base": "4", "value": "213" }
}
n: total number of shares provided

k: minimum number of shares required to reconstruct the secret

Each share contains:

base: numeric base of the value

value: share value in the specified base

## How to Run

Using a JSON File
Save your JSON input in a file (e.g., tc1.json).

Compile the Java code:
javac SecretSharing.java
Run the program with the JSON file as input:
java SecretSharing < tc1.json

## The program will output:

Secret: <reconstructed_secret>
Wrong shares: [list_of_wrong_share_keys]
Pasting JSON Directly

 Compile and run the program:
javac SecretSharing.java
java SecretSharing
Paste your JSON input directly into the console.
Press Ctrl+D (Linux/Mac) or Ctrl+Z (Windows) to signal the end of input.
The output will display the reconstructed secret and any wrong shares.

Output
Secret: The reconstructed secret (constant term of the polynomial)

Wrong shares: List of share keys that are inconsistent with the majority reconstruction

Secret: 79836264049851
Wrong shares: [2, 8]
