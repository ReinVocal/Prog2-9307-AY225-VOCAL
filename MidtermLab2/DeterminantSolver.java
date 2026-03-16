/**
 * =====================================================
 * Student Name    : Rein Alexander A. Vocal
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : UPHSD Molino Campus
 * Date            : March 16, 2026
 *
 * Description:
 *   Computes the determinant of my assigned 3x3 matrix
 *   using cofactor expansion along the first row.
 *   Prints each step clearly.
 * =====================================================
 */
public class DeterminantSolver {

    // Step 1: Declare my matrix (hardcoded values)
    static int[][] matrix = {
        {3, 2, 5},   // Row 1
        {1, 4, 6},   // Row 2
        {2, 3, 1}    // Row 3
    };

    // Step 2: Helper to compute 2x2 determinant
    // Formula: ad - bc
    static int computeMinor(int a, int b, int c, int d) {
        return (a * d) - (b * c);
    }

    // Step 3: Print the matrix nicely
    static void printMatrix(int[][] m) {
        System.out.println("┌               ┐");
        for (int[] row : m) {
            System.out.printf("│  %2d  %2d  %2d  │%n", row[0], row[1], row[2]);
        }
        System.out.println("└               ┘");
    }

    // Step 4: Solve determinant step by step
    static void solveDeterminant(int[][] m) {
        System.out.println("====================================================");
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: Rein Alexander A. Vocal");
        System.out.println("  Assigned Matrix:");
        System.out.println("====================================================");
        printMatrix(m);
        System.out.println("====================================================");
        System.out.println("\nExpanding along Row 1 (cofactor expansion):\n");

        // Minor M11 → remove row 0, col 0
        int minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
        System.out.printf("  Step 1 — Minor M₁₁: det([%d,%d],[%d,%d]) = %d%n",
            m[1][1], m[1][2], m[2][1], m[2][2], minor11);

        // Minor M12 → remove row 0, col 1
        int minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
        System.out.printf("  Step 2 — Minor M₁₂: det([%d,%d],[%d,%d]) = %d%n",
            m[1][0], m[1][2], m[2][0], m[2][2], minor12);

        // Minor M13 → remove row 0, col 2
        int minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
        System.out.printf("  Step 3 — Minor M₁₃: det([%d,%d],[%d,%d]) = %d%n",
            m[1][0], m[1][1], m[2][0], m[2][1], minor13);

        // Cofactors (apply signs + - +)
        int c11 =  m[0][0] * minor11;
        int c12 = -m[0][1] * minor12;
        int c13 =  m[0][2] * minor13;

        System.out.println();
        System.out.printf("  Cofactor C₁₁ = (+1) × %d × %d = %d%n", m[0][0], minor11, c11);
        System.out.printf("  Cofactor C₁₂ = (-1) × %d × %d = %d%n", m[0][1], minor12, c12);
        System.out.printf("  Cofactor C₁₃ = (+1) × %d × %d = %d%n", m[0][2], minor13, c13);

        // Final determinant
        int det = c11 + c12 + c13;
        System.out.printf("%n  det(M) = %d + (%d) + %d%n", c11, c12, c13);
        System.out.println("====================================================");
        System.out.printf("  ✓  DETERMINANT = %d%n", det);

        if (det == 0) {
            System.out.println("  ⚠ The matrix is SINGULAR — it has no inverse.");
        }
        System.out.println("====================================================");
    }

    // Step 5: Program entry point
    public static void main(String[] args) {
        solveDeterminant(matrix);
    }
}
