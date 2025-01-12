// package main.java;
import java.lang.Math;  
import java.util.*; 
import java.io.*;
import java.awt.Color;
import java.awt.Image; 
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 

// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// -----------------------------------------------------------------------------------------------------------------------------------------------------------

public class javaGraphicalManipulator{
    /**
     * Converts an image into a pixel grid (2D array of RGB values).
     * Optionally, writes the pixel grid to a text file.
     *
     * @param imagePath Path to the input image.
     * @param outputToTextFile Boolean indicating whether to write the grid to a file.
     * @param textFile Path to the text file (required if outputToTextFile is true).
     * @return Pixel grid or written file.
     * @throws IOException If there's an issue with file handling.
     */
    public static Object imageToPixelGrid(String imagePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth();
        int height = image.getHeight();
        List<List<int[]>> pixelGrid = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<int[]> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                int[] pixel = {color.getRed(), color.getGreen(), color.getBlue()};
                row.add(pixel);
            }
            pixelGrid.add(row);
        }
        return pixelGrid;
    }

    /**
     * Converts a pixel grid back to an image and saves it.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param outputImagePath Path to save the output image.
     * @throws IOException If there is an issue with file saving.
     */
    public static void pixelGridToImage(int[][][] pixelGrid, String outputImagePath) throws IOException {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = pixelGrid[y][x][0];  // Red component
                int g = pixelGrid[y][x][1];  // Green component
                int b = pixelGrid[y][x][2];  // Blue component
                int rgb = new Color(r, g, b).getRGB();
                image.setRGB(x, y, rgb);
            }
        }
        File outputFile = new File(outputImagePath);
        ImageIO.write(image, "jpg", outputFile);
    }

// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Resizes the pixel grid to specified dimensions.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param length    New height of the image.
     * @param width     New width of the image.
     * @return Resized pixel grid.
     */
    public static int[][][] resizeImage(int[][][] pixelGrid, int length, int width) {
        int originalHeight = pixelGrid.length;
        int originalWidth = pixelGrid[0].length;
        BufferedImage image = new BufferedImage(originalWidth, originalHeight, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < originalHeight; y++) {
            for (int x = 0; x < originalWidth; x++) {
                int r = pixelGrid[y][x][0];  // Red component
                int g = pixelGrid[y][x][1];  // Green component
                int b = pixelGrid[y][x][2];  // Blue component
                int rgb = new Color(r, g, b).getRGB();
                image.setRGB(x, y, rgb);
            }
        }
        Image resizedImage = image.getScaledInstance(width, length, Image.SCALE_SMOOTH);
        BufferedImage resizedBufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
        resizedBufferedImage.getGraphics().drawImage(resizedImage, 0, 0, null);
        int[][][] resizedPixelGrid = new int[length][width][3];
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(resizedBufferedImage.getRGB(x, y));
                resizedPixelGrid[y][x][0] = color.getRed();
                resizedPixelGrid[y][x][1] = color.getGreen();
                resizedPixelGrid[y][x][2] = color.getBlue();
            }
        }
        return resizedPixelGrid;
    }

    /**
     * Crops the pixel grid to specified dimensions.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param top       Number of rows to remove from the top.
     * @param left      Number of columns to remove from the left.
     * @param right     Number of columns to remove from the right.
     * @param bottom    Number of rows to remove from the bottom.
     * @return Cropped pixel grid.
     */
    public static int[][][] cropImage(int[][][] pixelGrid, int top, int left, int right, int bottom) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int newHeight = height - top - bottom;
        int newWidth = width - left - right;
        int[][][] croppedPixelGrid = new int[newHeight][newWidth][3];
        for (int y = top; y < height - bottom; y++) {
            for (int x = left; x < width - right; x++) {
                croppedPixelGrid[y - top][x - left][0] = pixelGrid[y][x][0]; // Red
                croppedPixelGrid[y - top][x - left][1] = pixelGrid[y][x][1]; // Green
                croppedPixelGrid[y - top][x - left][2] = pixelGrid[y][x][2]; // Blue
            }
        }
        return croppedPixelGrid;
    }

// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Flips the pixel grid horizontally.
     *
     * @param pixelGrid 2D array of RGB values.
     * @return Horizontally flipped pixel grid.
     */
    public static int[][][] flipHoriz(int[][][] pixelGrid) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] flippedPixelGrid = new int[height][width][3];
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){flippedPixelGrid[y][x] = pixelGrid[y][width - 1 - x];}
        }
        return flippedPixelGrid;
    }

    /**
     * Flips the pixel grid vertically.
     *
     * @param pixelGrid 2D array of RGB values.
     * @return Vertically flipped pixel grid.
     */
    public static int[][][] flipVert(int[][][] pixelGrid) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] flippedPixelGrid = new int[height][width][3];
        for (int y = 0; y < height; y++){flippedPixelGrid[height - 1 - y] = pixelGrid[y];}
        return flippedPixelGrid;
    }

// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Converts the pixel grid to black and white.
     *
     * @param pixelGrid 2D array of RGB values.
     * @return Black and white pixel grid.
     */
    public static int[][][] bnw(int[][][] pixelGrid) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] bnwGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = pixelGrid[y][x][0];
                int g = pixelGrid[y][x][1];
                int b = pixelGrid[y][x][2];
                int average = (r + g + b) / 3;
                bnwGrid[y][x][0] = average;
                bnwGrid[y][x][1] = average;
                bnwGrid[y][x][2] = average;
            }
        }
        return bnwGrid;
    }

    /**
     * Pops a single color by retaining pixels close to the specified color and grayscaling others.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param r         Red component of the target color.
     * @param g         Green component of the target color.
     * @param b         Blue component of the target color.
     * @param threshold Maximum distance for color matching.
     * @return Color-popped pixel grid.
     */
    public static int[][][] singleColorPop(int[][][] pixelGrid, int r, int g, int b, int threshold) {
        int target[] = {r, g, b};
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] popGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelGrid[y][x];
                double distance = Math.sqrt(Math.pow(pixel[0] - target[0], 2) + 
                                            Math.pow(pixel[1] - target[1], 2) + 
                                            Math.pow(pixel[2] - target[2], 2));
                if (distance < threshold){popGrid[y][x] = pixel;} 
                else {
                    int average = (pixel[0] + pixel[1] + pixel[2]) / 3;
                    popGrid[y][x] = new int[]{average, average, average};
                }
            }
        }
        return popGrid;
    }

    /**
     * Removes a single color by grayscaling pixels close to the specified color.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param r         Red component of the target color.
     * @param g         Green component of the target color.
     * @param b         Blue component of the target color.
     * @param threshold Maximum distance for color matching.
     * @return Color-removed pixel grid.
     */
    public static int[][][] singleColorRemove(int[][][] pixelGrid, int r, int g, int b, int threshold) {
        int target[] = {r, g, b};
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] removeGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelGrid[y][x];
                double distance = Math.sqrt(Math.pow(pixel[0] - target[0], 2) + 
                                            Math.pow(pixel[1] - target[1], 2) + 
                                            Math.pow(pixel[2] - target[2], 2));
                if (distance < threshold){
                    int average = (pixel[0] + pixel[1] + pixel[2]) / 3;
                    removeGrid[y][x] = new int[]{average, average, average};
                }else{removeGrid[y][x] = pixel;}
            }
        }
        return removeGrid;
    }

    /**
     * Changes all occurrences of a target color to a replacement color.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param r1        Red component of the target color.
     * @param g1        Green component of the target color.
     * @param b1        Blue component of the target color.
     * @param r2        Red component of the replacement color.
     * @param g2        Green component of the replacement color.
     * @param b2        Blue component of the replacement color.
     * @param threshold Maximum distance for color matching.
     * @return Pixel grid with color replaced.
     */
    public static int[][][] changeColor(int[][][] pixelGrid, int r1, int g1, int b1, int r2, int g2, int b2, int threshold) {
        int target[] = {r1, g1, b1};
        int replacement[] = {r2, g2, b2};
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] modifiedGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelGrid[y][x];
                double distance = Math.sqrt(Math.pow(pixel[0] - target[0], 2) + 
                                            Math.pow(pixel[1] - target[1], 2) + 
                                            Math.pow(pixel[2] - target[2], 2));
                if (distance <= threshold){modifiedGrid[y][x] = replacement;}
                else{modifiedGrid[y][x] = pixel;}
            }
        }
        return modifiedGrid;
    }

// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// -----------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Adjusts shadow intensity by modifying darker pixels.
     * Positive factor darkens, negative factor lightens.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param factor    Adjustment factor (>0 to darken, <0 to lighten).
     * @return Shadow-intensity-adjusted pixel grid.
     */
    public static int[][][] adjustShadowIntensity(int[][][] pixelGrid, double factor) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] adjustedGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelGrid[y][x];
                int[] adjustedPixel = new int[3];
                for (int i = 0; i < 3; i++){
                    if (pixel[i] < 128){adjustedPixel[i] = (int) Math.min(255, Math.max(0, pixel[i] * factor));}
                    else{adjustedPixel[i] = pixel[i];}
                }
                adjustedGrid[y][x] = adjustedPixel;
            }
        }
        return adjustedGrid;
    }

    /**
     * Adjusts the black point of the image.
     * Positive level increases darkness, negative level decreases it.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param level     Adjustment level (>0 to darken, <0 to lighten).
     * @return Black-point-adjusted pixel grid.
     */
    public static int[][][] adjustBlackPoint(int[][][] pixelGrid, int level) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] adjustedGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelGrid[y][x];
                int[] adjustedPixel = new int[3];
                for (int i = 0; i < 3; i++){adjustedPixel[i] = Math.max(pixel[i] - level, 0);}
                adjustedGrid[y][x] = adjustedPixel;
            }
        }
        return adjustedGrid;
    }

    /**
     * Adjusts the brightness of the image.
     * Positive factor increases brightness, negative factor decreases it.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param factor    Adjustment factor (>0 to brighten, <0 to darken).
     * @return Brightness-adjusted pixel grid.
     */
    public static int[][][] adjustBrightness(int[][][] pixelGrid, double brightnessFactor) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] adjustedPixelGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelGrid[y][x];
                int[] adjustedPixel = new int[3];
                for (int i = 0; i < 3; i++){adjustedPixel[i] = Math.min(Math.max((int) (pixel[i] * brightnessFactor), 0), 255);}
                adjustedPixelGrid[y][x] = adjustedPixel;
            }
        }
        return adjustedPixelGrid;
    }

    /**
     * Adjusts the warmth of the image.
     * Positive factor increases red/yellow tones, negative factor increases blue tones.
     *
     * @param pixelGrid 2D array of RGB values.
     * @param factor    Adjustment factor (>0 to warm, <0 to cool).
     * @return Warmth-adjusted pixel grid.
     */
    public static int[][][] adjustWarmth(int[][][] pixelGrid, double warmthFactor) {
        int height = pixelGrid.length;
        int width = pixelGrid[0].length;
        int[][][] adjustedPixelGrid = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelGrid[y][x];
                int[] adjustedPixel = new int[3];
                adjustedPixel[0] = Math.min(Math.max((int) (pixel[0] * (1 + warmthFactor)), 0), 255);  // Increase red
                adjustedPixel[1] = Math.min(Math.max((int) (pixel[1] * (1 + warmthFactor)), 0), 255);  // Increase green
                adjustedPixel[2] = Math.min(Math.max((int) (pixel[2] * (1 - warmthFactor)), 0), 255);  // Decrease blue
                adjustedPixelGrid[y][x] = adjustedPixel;
            }
        }
        return adjustedPixelGrid;
    }
}

// -----------------------------------------------------------------------------------------------------------------------------------------------------------
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// -----------------------------------------------------------------------------------------------------------------------------------------------------------
