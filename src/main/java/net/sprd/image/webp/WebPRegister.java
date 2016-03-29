/*
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

import java.util.Iterator;
import javax.imageio.spi.IIORegistry;
import javax.imageio.spi.ImageWriterSpi;

/**
 *
 * @author ran
 */
public class WebPRegister {

    public static final void registerImageTypes() {
        Iterator<ImageWriterSpi> providers = IIORegistry.getDefaultInstance().getServiceProviders(
                javax.imageio.spi.ImageWriterSpi.class, true);
        if (providers != null) {
            boolean found = false;
            for (; providers.hasNext();) {
                ImageWriterSpi next = providers.next();
                if (next.getClass().getName().equals("WebPImageWriterSpi")) {
                    found = true;
                }
            }

            if (!found) {
                IIORegistry.getDefaultInstance().registerServiceProvider(new WebPImageWriterSpi());
            }
        }

    }
    
}
