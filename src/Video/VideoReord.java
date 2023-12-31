package Video;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import atu.testrecorder.media.Format;
import atu.testrecorder.media.avi.AbstractAVIStream.MediaType;

public class VideoReord {
    private ScreenRecorder screenRecorder;
 
     public static void main(String[] args) throws Exception {
                                                                
             VideoReord  videoReord = new VideoReord();
             videoReord.startRecording();                                       
                      
             WebDriver driver = new ChromeDriver();                                    
             driver.get("http://www.google.com");
                         
             WebElement element = driver.findElement(By.name("q"));                                     
             element.sendKeys("testing");                                    
             element.submit();                   
             System.out.println("Page title is: " + driver.getTitle());                                                                       
             driver.quit();                              
             videoReord.stopRecording();
       }
 

       public void startRecording() throws Exception
       {    
              File file = new File("D:\\Videos");
                           
              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
              int width = screenSize.width;
              int height = screenSize.height;
                            
              Rectangle captureSize = new Rectangle(0,0, width, height);
                            
            GraphicsConfiguration gc = GraphicsEnvironment
               .getLocalGraphicsEnvironment()
               .getDefaultScreenDevice()
               .getDefaultConfiguration();

           this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
               new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
               new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                    QualityKey, 1.0f,
                    KeyFrameIntervalKey, 15 * 60),
               new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                    FrameRateKey, Rational.valueOf(30)),
               null, file, "MyVideo");
          this.screenRecorder.start();
      
       }

       public void stopRecording() throws Exception
       {
         this.screenRecorder.stop();
       }
}