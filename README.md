# Shamir Secret Sharing Reconstruction

A Java implementation of **Shamir's Secret Sharing** scheme that reconstructs the secret from shares and identifies wrong or malicious shares. The program supports large numbers and mixed numeric bases.

## Features

- Accepts user input JSON containing `n` shares and minimum `k` shares required to reconstruct the secret.
- Supports shares in different bases (binary, decimal, octal, hexadecimal, etc.).
- Computes the secret (constant term of the polynomial) using **Lagrange interpolation** at x = 0.
- Detects incorrect or malicious shares automatically.
- Handles very large numbers with `BigInteger`.
- Lightweight and easy-to-read Java code.
