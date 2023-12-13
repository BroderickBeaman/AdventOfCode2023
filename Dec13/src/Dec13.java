import java.util.List;

public class Dec13 {
    public static void main(String[] args) {
        List<Character[][]> puzzleGrids = InputLoader.loadInput();


        long answer = puzzleGrids.stream().mapToLong(Dec13::solvePuzzle).sum();

        System.out.println("Answer: " + answer);
    }

    public static boolean areRowsEqual(Character[][] puzzleGrid, int row1, int row2) {
        boolean areSame = true;
        for (int i = 0; i < puzzleGrid[0].length; i++) {
            if (!puzzleGrid[row1][i].equals(puzzleGrid[row2][i])) {
                areSame = false;
                break;
            }
        }
        return areSame;
    }

    public static boolean areColumnsEqual(Character[][] puzzleGrid, int col1, int col2) {
        boolean areSame = true;
        for (int i = 0; i < puzzleGrid.length; i++) {
            if (!puzzleGrid[i][col1].equals(puzzleGrid[i][col2])) {
                areSame = false;
                break;
            }
        }
        return areSame;
    }


    public static long findMirrorLine(Character[][] puzzleGrid, Long original) {

        int height = puzzleGrid.length;
        int width = puzzleGrid[0].length;

        // Try to find vertical mirror
        boolean verticalMirror = false;
        int mirrorToRightOf = 0;
        for (; mirrorToRightOf < (width - 1); mirrorToRightOf++) {
            boolean foundMirror = true;
            int col1 = mirrorToRightOf;
            int col2 = mirrorToRightOf + 1;

            while (col1 >= 0 && col2 <= (width - 1)) {
                foundMirror = areColumnsEqual(puzzleGrid, col1, col2);
                if (!foundMirror) {
                    break;
                }
                col1--;
                col2++;
            }
            if (foundMirror && original != null) {
                if ((mirrorToRightOf + 1) != original) {
                    return (mirrorToRightOf + 1);
                }
            } else {
                verticalMirror = foundMirror;
            }
            if (verticalMirror) {
                return mirrorToRightOf + 1;
            }
        }

        // Try to find horizontal mirror
        boolean horizontalMirror = false;
        int mirrorUnder = 0;
        for (; mirrorUnder < (height - 1); mirrorUnder++) {
            boolean foundMirror = true;
            int row1 = mirrorUnder;
            int row2 = mirrorUnder + 1;

            while (row1 >= 0 && row2 <= (height - 1)) {
                foundMirror = areRowsEqual(puzzleGrid, row1, row2);
                if (!foundMirror) {
                    break;
                }
                row1--;
                row2++;
            }
            if (foundMirror && original != null) {
                if (((mirrorUnder + 1) * 100L) != original) {
                    return (mirrorUnder + 1) * 100L;
                }
            } else {
                horizontalMirror = foundMirror;
            }
            if (horizontalMirror) {
                return (mirrorUnder + 1) * 100L;
            }
        }

        return 0;
    }

    public static Long solvePuzzle(Character[][] puzzle) {
        Long originalAnswer = findMirrorLine(puzzle, null);
        int height = puzzle.length;
        int width = puzzle[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                puzzle[y][x] = opposite(puzzle[y][x]);
                Long newAnswer = findMirrorLine(puzzle, originalAnswer);
                if (newAnswer != 0) {
                    return newAnswer;
                }
                puzzle[y][x] = opposite(puzzle[y][x]);
            }
        }
        return originalAnswer;
    }

    public static Character opposite(Character character) {
        return character.equals('.') ? '#' : '.';
    }

}
