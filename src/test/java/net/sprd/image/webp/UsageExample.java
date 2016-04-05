package net.sprd.image.webp;

import com.google.webp.libwebp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.lang.reflect.Method;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import org.scijava.nativelib.NativeLibraryUtil;

/**
 *
 * @author ran
 */
public class UsageExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        NativeLibraryUtil.loadNativeLibrary(UsageExample.class, "webp_jni");
        final int version = libwebp.WebPGetDecoderVersion();
        System.out.println("libwebp version: " + Integer.toHexString(version));

        System.out.println("libwebp methods:");
        final Method[] libwebpMethods = libwebp.class.getDeclaredMethods();
        for (int i = 0; i < libwebpMethods.length; i++) {
            System.out.println(libwebpMethods[i]);
        }

        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");
        String osVersion = System.getProperty("os.version");

        System.out.println("OS-NAME :" + osName);
        System.out.println("OS-ARCH :" + osArch);
        System.out.println("OS-VERS :" + osVersion);

        String distro = fromStream(Runtime.getRuntime().exec("lsb_release -i -s").getInputStream());
        System.out.println("OS-DISTRO :" + distro);
        
        
        String origFileName = "src/test/resources/test.png";
        BufferedImage img = ImageIO.read(new File(origFileName));

        WebPRegister.registerImageTypes();

        File f1 = new File(origFileName);
        System.out.println("size of orig image file :" + f1.length());

        System.out.println("lossy webp size (0.5):" + toWebp("src/test/resources/lossy1.webp", img, 0.5f).length());
        System.out.println("lossy webp size (0.8):" + toWebp("src/test/resources/lossy2.webp", img, 0.8f).length());
        System.out.println("lossy webp size (1.0):" + toWebp("src/test/resources/lossy3.webp", img, 1f).length());
        System.out.println("lossless webp size :" + toWebp("src/test/resources/lossless.webp", img, -1f).length());
    }

    private static File toWebp(String fileName, BufferedImage img, float quality) throws IOException {
        File f = new File(fileName);
        try {
            f.delete();
        } catch (Exception e) {
        }
        Iterator<ImageWriter> writerList = ImageIO.getImageWritersByFormatName("webp");
        ImageWriter writer = writerList.next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        WebPWriteParam writeParam = (WebPWriteParam) param;
        if (quality < 0) {
            writeParam.setCompressionType(WebPWriteParam.LOSSLESS);
        } else {
            writeParam.setCompressionQuality(quality);
        }
        ImageOutputStream ios = ImageIO.createImageOutputStream(new File(fileName));
        writer.setOutput(ios);
        IIOImage outimage = new IIOImage(img, null, null);
        writer.write(null, outimage, writeParam);
        return f;
    }

    public static String fromStream(InputStream in) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String newLine = System.getProperty("line.separator");
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
                out.append(newLine);
            }
            return out.toString();
        } catch (Exception e) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
  
            }
        }
        return "";
    }

}
