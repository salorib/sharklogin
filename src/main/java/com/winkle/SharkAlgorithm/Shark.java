/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winkle.SharkAlgorithm;

import static java.lang.Math.pow;

/**
 *
 * @author Argos
 */
public class Shark {

    private static int length;
    private static String Text;
    private static String hash;

    public static String init(String args) {
        Text = args;

        int[][] matrix = toMatrix(toArrayInt(padAndAppendx64(padding(joinAndApendOne(toEightBits(toBinary(toAscii(Text))))))));

        int[][] a = extendToEighty(matrix);

        int[] A = new int[32];
        int[] B = new int[32];
        int[] C = new int[32];
        int[] D = new int[32];
        int[] E = new int[32];

        for (int i = 0; i < 80; i += 5) {
            for (int j = 0; j < 32; j++) {
                A[j] = a[i][j];
                B[j] = a[i + 1][j];
                C[j] = a[i + 2][j];
                D[j] = a[i + 3][j];
                E[j] = a[i + 4][j];
            }

            A = Or(And(B, A), And(Not(B), D));
            B = Xor(D, Xor(B, C));
            C = Or(And(B, E), Or(And(B, D), And(C, D)));
            D = And(Or(B, C), Xor(E, A));
            E = Or(And(C, A), And(Not(C), E));

            for (int j = 0; j < 32; j++) {
                a[i][j] = A[j];
                a[i + 1][j] = B[j];
                a[i + 2][j] = C[j];
                a[i + 3][j] = D[j];
                a[i + 4][j] = E[j];
            }

        }

        for (int i = 79; i > 0; i -= 5) {
            for (int j = 0; j < 32; j++) {
                A[j] = a[i][j];
                B[j] = a[i - 1][j];
                C[j] = a[i - 2][j];
                D[j] = a[i - 3][j];
                E[j] = a[i - 4][j];
            }

            E = Or(And(B, A), And(Not(B), D));
            D = Xor(D, Xor(B, C));
            C = Or(And(B, E), Or(And(B, D), And(C, D)));
            B = And(Or(B, C), Xor(E, A));
            A = Or(And(C, A), And(Not(C), E));

            for (int j = 0; j < 32; j++) {
                a[i][j] = A[j];
                a[i - 1][j] = B[j];
                a[i - 2][j] = C[j];
                a[i - 3][j] = D[j];
                a[i - 4][j] = E[j];
            }

        }

        for (int i = 0; i < 80; i += 5) {
            for (int j = 0; j < 32; j++) {
                A[j] = a[i][j];
                B[j] = a[i + 1][j];
                C[j] = a[i + 2][j];
                D[j] = a[i + 3][j];
                E[j] = a[i + 4][j];
            }

            A = Or(And(B, A), And(Not(B), D));
            B = Xor(D, Xor(B, C));
            C = Or(And(B, E), Or(And(B, D), And(C, D)));
            D = And(Or(B, C), Xor(E, A));
            E = Or(And(C, A), And(Not(C), E));

            for (int j = 0; j < 32; j++) {
                a[i][j] = A[j];
                a[i + 1][j] = B[j];
                a[i + 2][j] = C[j];
                a[i + 3][j] = D[j];
                a[i + 4][j] = E[j];
            }

        }

        hash = "$";
        hash = hash + toHash(A);
        hash = hash + toHash(B);
        hash = hash + toHash(C);
        hash = hash + toHash(D);
        hash = hash + toHash(E);

        //System.out.println(hash);

        return hash;
    }

    /**
     * Convert text to ascii and return an array of it
     *
     * @param str is the text
     * @return array int[] of ascii values
     */
    private static int[] toAscii(String str) {
        int[] array = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            array[i] = (int) str.charAt(i);
        }
        return array;
    }

    /**
     * Receive an ascii array and converts to a String array with values
     *
     * @param ascii
     * @return string array with binary values
     */
    private static String[] toBinary(int[] ascii) {
        String[] binaryArray = new String[ascii.length];
        for (int i = 0; i < ascii.length; i++) {
            binaryArray[i] = toBinary(ascii[i]);
        }
        return binaryArray;
    }

    /**
     * transforms an int value to binary code and returns it
     *
     * @param val to transform to binary
     * @return binary value from @param val in string format
     */
    private static String toBinary(int val) {
        return Integer.toBinaryString(val);
    }

    /**
     * Receibe a String array wich contains all ascii values on binary and
     * completes to eight bits long
     *
     * @param binary
     * @return String array
     */
    private static String[] toEightBits(String[] binary) {
        for (int i = 0; i < binary.length; i++) {
            while (binary[i].length() < 8) {
                binary[i] = "0" + binary[i];
            }
        }
        return binary;
    }

    /**
     * Receibe an String array extract the length and returns the join of sub
     * strings into array as an String
     *
     * @param array
     * @return String
     */
    private static String joinAndApendOne(String[] array) {
        length = join(array).length();
        return appendOne(join(array));
    }

    /**
     * join the substrings of an array String and returns as String
     *
     * @param array
     * @return
     */
    private static String join(String[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str += array[i];
        }
        return str;
    }

    /**
     * join the substring of an array int and returns as string
     *
     * @param array
     * @return
     */
    private static String join(int[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str += array[i];
        }
        return str;
    }

    /**
     * just put one to the end of string
     *
     * @param binary
     * @return String
     */
    private static String appendOne(String binary) {
        return binary += "1";
    }

    /**
     * Fill with ceros the string and returns it
     *
     * @param str
     * @return
     */
    private static String padding(String str) {
        while (str.length() % 512 != 448) {
            str += "0";
        }
        return str;
    }

    /**
     * Fill with ceros and apend the length of the firs array and returns it
     *
     * @param arraybinary
     * @return
     */
    private static String padAndAppendx64(String arraybinary) {
        String lengthbinary = toBinary(length);
        lengthbinary = untilx64(lengthbinary);
        return arraybinary + lengthbinary;

    }

    /**
     * just fill with ceros until string reaches 64 length
     *
     * @param binary
     * @return
     */
    private static String untilx64(String binary) {
        while (binary.length() < 64) {
            binary = "0" + binary;
        }
        return binary;
    }

    /**
     * Convers an String array to array int and returns
     *
     * @param binary
     * @return
     */
    private static int[] toArrayInt(String binary) {
        int[] array = new int[binary.length()];
        for (int i = 0; i < binary.length(); i++) {
            array[i] = Integer.parseInt(String.valueOf(binary.charAt(i)));
        }
        return array;
    }

    /**
     * Create a matrix from array int
     *
     * @param array
     * @return
     */
    private static int[][] toMatrix(int[] array) {
        int[][] matrix = new int[16][32];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
                matrix[i][j] = array[k];
                k++;
            }
        }
        return matrix;
    }

    /**
     * Extends matrix form 16 to 80 and fills with the original text on binary
     * consecutivaly
     *
     * @param matrix16
     * @return
     */
    private static int[][] extendToEighty(int[][] matrix16) {
        int[][] matrix80 = new int[80][32];

        int[] array = toArrayInt(joinAndApendOne(toEightBits(toBinary(toAscii(Text)))));

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
                matrix80[i][j] = matrix16[i][j];
            }
        }

        int k = 0;
        for (int i = 16; i < 80; i++) {
            for (int j = 0; j < 32; j++) {
                if (k == array.length) {
                    k = 0;
                }
                matrix80[i][j] = array[k];
                k++;
            }
        }
        return matrix80;
    }

    private static int[] And(int[] a, int[] b) {
        int[] c = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1 && b[i] == 1) {
                c[i] = 1;
            } else {
                c[i] = 0;
            }
        }
        return c;
    }

    private static int[] Or(int[] a, int[] b) {
        int[] c = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1 || b[i] == 1) {
                c[i] = 1;
            } else {
                c[i] = 0;
            }
        }
        return c;
    }

    private static int[] Xor(int[] a, int[] b) {
        int[] c = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1 && b[i] == 1) {
                c[i] = 0;
            } else if (a[i] == 0 && b[i] == 0) {
                c[i] = 0;
            } else {
                c[i] = 1;
            }
        }
        return c;
    }

    private static int[] Not(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1) {
                a[i] = 0;
            } else {
                a[i] = 1;
            }
        }
        return a;
    }

    private static String toHash(int[] c) {
        int n = 1;
        int dec = 0;
        for (int i = 0; i < 32; i++) {
            n = (int) pow(2, i);
            if (c[31 - i] == 1) {
                dec += n;
            }
        }
        return Integer.toHexString(dec);
    }

}
