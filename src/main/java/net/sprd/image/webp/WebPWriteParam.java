/*
 * Copyright 2013 Luciad (http://www.luciad.com)
 * Copyright 2016 sprd.net AG (https://www.spreadshirt.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sprd.image.webp;

import javax.imageio.ImageWriteParam;
import java.util.Locale;

public class WebPWriteParam extends ImageWriteParam {
    
    public static final String 
            LOSSLESS = "Lossless",
            LOSSY = "Lossy";
    

  public WebPWriteParam( Locale locale ) {
    super( locale );
    canWriteCompressed = true;
    compressionTypes = new String[]{LOSSY, LOSSLESS};
    // default compression type is LOSSY
    compressionType = compressionTypes[0]; 
    compressionMode = MODE_EXPLICIT;
  }

  @Override
  public float getCompressionQuality() {
    return super.getCompressionQuality();
  }

  @Override
  public void setCompressionQuality( float quality ) {
    super.setCompressionQuality(quality );
  }
  
  public boolean isLossyType() {
      return LOSSY.equalsIgnoreCase( super.getCompressionType());
  }
  
  public boolean isLosslessType() {
      return LOSSLESS.equalsIgnoreCase( super.getCompressionType());
  }

}
