import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * 
 *
 * @author 
 * @version 1.0 
 */
public class ImageViewer11
{
    // class constants
    private static final Color BACKGROUND_COLOUR = Color.WHITE;
    private static final int DELAY = 4000;
    private static final String ERROR_IMAGE_UNAVAILABLE = "Yikes! Unable to display image.";
    private static final int FRAME_HEIGHT = 600;
    private static final String FRAME_TITLE = "Image Viewer";
    private static final int FRAME_WIDTH = 800;
    private static final String[] IMAGE_BUTTON_TEXT ={"A", "B", "C"};
    private static final String[] IMAGE_CREDIT =
        {"http://www.wackybuttons.com/buttonstore/Alphabet/Lower%20Case%20Letters.php [letter a]",
            "http://www.wackybuttons.com/buttonstore/Alphabet/Lower%20Case%20Letters.php [letter b]",
            "http://www.wackybuttons.com/buttonstore/Alphabet/Lower%20Case%20Letters.php [letter c]"};
    private static final String[] IMAGE_SOURCE = {"images/translate.png", "images/b.jpeg", "images/c.jpeg"};
    private static final int NUMBER_OF_IMAGES = 3;

    // instance fields
    private JPanel controlButtonPanel;
    private JButton disappearButton;
    private JFrame frame;
    private ImageComponent[] image;
    private JPanel imageButtonPanel;
    private JLabel[] imageCreditLabel;
    private JButton quitButton;
    private JButton[] setImageButton;

    /* constructors */

    /**
     * Creates an ImageViewer with a fixed image.
     */
    public ImageViewer11()
    {
        makeFrame();
    } // end of constructor ImageViewer11()

    /* mutators */

    /**
     * Sets the visibility of this image viewer.
     * 
     * @param shouldBeVisible <code>true</code>, if this image viewer should
     * be visible; otherwise <code>false</code>
     */
    public void setVisible(boolean shouldBeVisible)
    {
        frame.setVisible(shouldBeVisible);
    } // end of method setVisible(boolean shouldBeVisible)

    /**
     * Sets the visibility of this image viewer.
     * 
     * @param shouldBeVisible <code>true</code>, if this image viewer should
     * be visible; otherwise <code>false</code>
     * @param delay the time in milliseconds the changed visibility should persist
     */
    public void setVisible(boolean shouldBeVisible, int delay)
    {
        if (delay < 0) return;

        try
        {
            frame.setVisible(shouldBeVisible);
            Thread.sleep(delay);
            frame.setVisible(!shouldBeVisible);
        }
        catch (InterruptedException exception)
        {
            /*
             * InterruptedException thrown when another thread interrupts
             * the current thread while sleep is active. Since this application
             * has not defined another thread that could cause the interruption,
             * no action need be taken here.
             * (Ref: http://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html)
             */
        } // end of catch (InterruptedException exception)

    } // end of method setVisible(boolean shouldBeVisible)

    /* private methods */

    /*
     * Load image data for later use.
     */
    private void loadImageData()
    {
        image = new ImageComponent[NUMBER_OF_IMAGES];
        imageCreditLabel = new JLabel[NUMBER_OF_IMAGES];

        for (int imageNumber = 0; imageNumber < NUMBER_OF_IMAGES; imageNumber++)
        {
            image[imageNumber] = new ImageComponent(IMAGE_SOURCE[imageNumber]);
            imageCreditLabel[imageNumber] = new JLabel(IMAGE_CREDIT[imageNumber]);
        } // end of  for (int imageNumber = 0; imageNumber < NUMBER_OF_IMAGES; imageNumber++)
    } // end of method loadImageData()

    /*
     * Creates the control-buttons panel.
     */
    private void makeControlButtonPanel()
    {
        controlButtonPanel = new JPanel();
        controlButtonPanel.setBackground(BACKGROUND_COLOUR);

        ControlButtonListener actionListener = new ControlButtonListener();

        disappearButton = new JButton("disappear");
        disappearButton.addActionListener(actionListener);
        controlButtonPanel.add(disappearButton);

        quitButton = new JButton("quit");
        quitButton.addActionListener(actionListener);
        controlButtonPanel.add(quitButton);
    } // end of method makeControlButtonPanel()

    /*
     * Creates the application frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame(FRAME_TITLE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.getContentPane().setBackground(BACKGROUND_COLOUR);

        // Display the default (ie: first) image.
        loadImageData();
        if (image[0] != null)
        {
            frame.add(image[0], BorderLayout.CENTER);
            frame.add(imageCreditLabel[0], BorderLayout.PAGE_START);
        } // end of if (image[0] != null)

        makeImageButtonPanel();
        frame.add(imageButtonPanel, BorderLayout.LINE_START);

        makeControlButtonPanel();
        frame.add(controlButtonPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
    } // end of method makeFrame()

    /*
     * Creates the image-buttons panel.
     */
    private void makeImageButtonPanel()
    {
        imageButtonPanel = new JPanel();
        imageButtonPanel.setBackground(BACKGROUND_COLOUR);

        ImageButtonListener actionListener = new ImageButtonListener();

        setImageButton = new JButton[NUMBER_OF_IMAGES];

        for (int buttonNumber = 0; buttonNumber < NUMBER_OF_IMAGES; buttonNumber++)
        {
            setImageButton[buttonNumber] = new JButton(IMAGE_BUTTON_TEXT[buttonNumber]);
            setImageButton[buttonNumber].addActionListener(actionListener);
            imageButtonPanel.add(setImageButton[buttonNumber]);
        } // end of for (int buttonNumber = 0; buttonNumber < NUMBER_OF_IMAGES; buttonNumber++)
    } // end of method makeImageButtonPanel()

    /* private classes */

    /*
     * A listener which can be registered by an event source and which
     * can receive event objects.
     */
    private class ControlButtonListener implements ActionListener
    {
        /*
         * Responds to control-button events.
         */
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();

            if (source == disappearButton)
            {
                setVisible(false, DELAY);
            } // end of if (source == disappearButton)
            else if (source == quitButton)
            {
                System.exit(0);
            } // end of if (source == quitButton)

        } // end of method actionPerformed(ActionEvent event)
    } // end of class ControlButtonListener

    /*
     * A listener which can be registered by an event source and which
     * can receive event objects.
     */
    private class ImageButtonListener implements ActionListener
    {
        /*
         * Responds to image-button events.
         */
        public void actionPerformed(ActionEvent event)
        {
            Object source = event.getSource();

            for (int buttonNumber = 0; buttonNumber < NUMBER_OF_IMAGES; buttonNumber++)
            {
                if (source == setImageButton[buttonNumber])
                {
                    // Remove and replace the existing image.                   
                    BorderLayout layout = (BorderLayout)(frame.getContentPane()).getLayout();
                    Component component = layout.getLayoutComponent(BorderLayout.CENTER);
                    frame.remove(component);
                    frame.add(image[buttonNumber], BorderLayout.CENTER);

                    // Remove and replace the existing credit label.
                    component = layout.getLayoutComponent(BorderLayout.PAGE_START);
                    frame.remove(component);
                    frame.add(imageCreditLabel[buttonNumber], BorderLayout.PAGE_START);

                    frame.pack();
                    frame.setVisible(true);
                    frame.repaint();
                } // end of if (source == setImageButton[buttonNumber])
            } // end of for (int buttonNumber = 0; buttonNumber < NUMBER_OF_BUTTONS; buttonNumber++)
        } // end of method actionPerformed(ActionEvent event)
    } // end of class ImageButtonListener

    private class ImageComponent extends Component
    {
        // class fields
        private static final int NO_PROBLEMS_ENCOUNTERED = 0;
        private static final int PROBLEMS_ENCOUNTERED = -1;

        // instance fields
        private BufferedImage bufferedImage;
        private int status;

        /* constructors */

        /*
         * Creates a component with a drawn image. If the image was
         * drawn, the component's status is NO_PROBLEMS_ENCOUNTERED;
         * otherwise, PROBLEMS_ENCOUNTERED.
         */
        public ImageComponent(String fileName)
        {
            bufferedImage = null;
            status = NO_PROBLEMS_ENCOUNTERED;
            try
            {
                bufferedImage = ImageIO.read(new File(fileName));
            }
            catch (IOException exception)
            {
                status = PROBLEMS_ENCOUNTERED;
            } // end of catch (IOException exception)
        } // end of constructor ImageComponent(String fileName)

        /* accessors */

        /*
         * Returns the status of this component: NO_PROBLEMS_ENCOUNTERED
         * or PROBLEMS_ENCOUNTERED.
         */
        public int getStatus()
        {
            return status;
        } // end of method getStatus()

        /*
         * Returns a string representation of this component.
         * 
         * @return a string representing this component
         */
        public String toString()
        {
            return
            getClass().getName()
            + "["
            + "buffered image: " + bufferedImage
            + ", status: " + status
            + "]";
        } // end of method toString()

        /* mutators */

        /*
         * Called when the contents of the component should be painted, such as
         * when the component is first being shown or is damaged and in need of
         * repair.
         */
        public void paint(Graphics graphicsContext)
        {
            graphicsContext.drawImage(bufferedImage, 0, 0, null);
        } // end of method paint(Graphics graphicsContext)

    } // end of class ImageComponent

} // end of class ImageViewer11