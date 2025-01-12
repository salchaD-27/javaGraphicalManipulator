javaGraphicalManipulator
========================

**javaGraphicalManipulator** is a Java library designed for advanced image manipulation using pixel grids. It offers functions for resizing, cropping, color adjustments, and creative effects such as black-and-white conversion, color isolation, and brightness control. This library is ideal for developers working with custom image processing workflows in Java.

Features
--------

*   **Image Manipulation**
    
    *   Resize and crop images.
        
    *   Flip images horizontally or vertically.
        
*   **Color Transformations**
    
    *   Convert images to black and white.
        
    *   Isolate or remove specific colors.
        
    *   Replace specific colors with new ones.
        
*   **Adjustments**
    
    *   Brightness, shadow intensity, and black point adjustments.
        
    *   Warmth and fade effects for aesthetic modifications.
        
*   **I/O Operations**
    
    *   Convert images to pixel grids and vice versa.
        
    *   Export pixel grids to text files for external processing.
        

Installation
------------

To include **javaGraphicalManipulator** in your Java project, download the `javaGraphicalManipulator.class` file and save in your working directory
You can then access all the methods of the library

Quick Start
-----------

Here’s how to get started with javaGraphicalManipulator:

#### 1\. Convert an image to a pixel grid:

`import javaGraphicalManipulator.imageToPixelGrid;`
`BufferedImage image = imageToPixelGrid("example.jpg");`

#### 2\. Apply a transformation:

`import javaGraphicalManipulator.bnw;`
`BufferedImage bnwImage = bnw(pixelGrid);`

#### 3\. Convert back to an image:

`import javaGraphicalManipulator.pixelGridToImage;`
`pixelGridToImage(bnwImage, "output.jpg");`


Available Methods
-----------------

### **Image Manipulation**

*   `imageToPixelGrid(imagePath, outputToTextFile=False, textFile=None)`
    
*   `pixelGridToImage(pixelGrid, outputImagePath)`
    
*   `resizeImage(pixelGrid, length, width)`
    
*   `cropImage(pixelGrid, top, left, right, bottom)`
    
*   `flipHoriz(pixelGrid)`
    
*   `flipVert(pixelGrid)`
    

### **Color Transformations**

*   `bnw(pixelGrid)`
    
*   `singleColorPop(pixelGrid, r, g, b, threshold=50)`
    
*   `singleColorRemove(pixelGrid, r, g, b, threshold=50)`
    
*   `changeColor(pixelGrid, r1, g1, b1, r2, g2, b2, threshold=0)`
    

### **Adjustments**

*   `adjustBrightness(pixelGrid, factor)`
    
*   `adjustShadowIntensity(pixelGrid, factor)`
    
*   `adjustBlackPoint(pixelGrid, level)`
    
*   `adjustWarmth(pixelGrid, factor)`
    

Tests
-----

The tests folder includes unit tests to validate the functionality of the library. To run the tests, use your preferred Java testing framework (e.g., JUnit). If using Maven, run:
`mvn test`

Or if using Gradle:
`gradle test`

Contributing
------------

Contributions are welcome! Please fork this repository and submit a pull request for any bug fixes or enhancements.

License
-------

This project is licensed under the MIT License. See the LICENSE file for details.

Author
------

Developed by **Dharyansh Achlas** For any queries, feel free to contact **https://github.com/salchaD-27**