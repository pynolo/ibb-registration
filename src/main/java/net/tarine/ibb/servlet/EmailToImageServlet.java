package net.tarine.ibb.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * @author Abhijit Ghosh
 * @version 1.0
 */
public class EmailToImageServlet extends HttpServlet {
    private static final long serialVersionUID = -1761346889117186607L;
 
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    private static final int IMG_WIDTH = 300;
    private static final int IMG_HEIGHT = 25;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parameters
    	String username = request.getParameter("username");
    	if (username == null) username = "";
    	if (username.length() == 0) username = "--";
    	String domain = request.getParameter("domain");
    	if (domain == null) domain = "";
    	if (domain.length() == 0) domain = "--";
    	
    	// set mime type as jpg image
        response.setContentType("image/jpg");
        ServletOutputStream out = response.getOutputStream();
 
        BufferedImage bufferedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_BYTE_INDEXED);
 
        Graphics2D graphics = bufferedImage.createGraphics();
         
        // Set back ground of the generated image to white
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
 
        // set gradient font of text to be converted to image
        //GradientPaint gradientPaint = new GradientPaint(10, 5, Color.BLUE, 20, 10, Color.LIGHT_GRAY, true);
        graphics.setPaint(Color.BLUE);
        Font font = new Font("sans-serif", Font.BOLD, 15);
        graphics.setFont(font);
 
        // write email string in the image
        String email = username+"@"+domain;
        graphics.drawString(email, 0, 15);
 
        // release resources used by graphics context
        graphics.dispose();
 
        // encode the image as a JPEG data stream and write the same to servlet output stream  
        ImageIO.write(bufferedImage, "jpg", response.getOutputStream());  
 
        // close the stream
        out.close();
    }
}