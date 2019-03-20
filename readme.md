# sprd-webp
sprd-webp is a java wrapper around googles libwebp for generating webp encoded images. It is inspired by [webp 
project of Luciad](https://bitbucket.org/luciad/webp-imageio). Currently sprd-webp includes version 1.0.2 of 
[googles libwebp library](https://developers.google.com/speed/webp/docs/api). 

# Testing and Examples

You can find a simple example with different compression rates here:

    src/test/java/net/sprd/image/webp/UsageExample.java


# Travis-ci and releases

[see](https://travis-ci.org/spreadshirt/sprd-webp)


# Prerequirements


## Google WebP library
Use the shipped native libwebp library (1.0.2) for linux (src/main/resources/META-INF/lib) or download the latest precompiled version for your OS from
[https://developers.google.com/speed/webp/](https://developers.google.com/speed/webp/docs/precompiled#getting_cwebp_dwebp_and_the_webp_libraries).
and put it into the META-INF/lib folder.
If you need a shared library (as shipped by that project) clone [github mirror of webm project](https://github.com/webmproject/libwebp) 

    cd libwebp
    make -f makefile.unix
    cd swig
    gcc -DPIC -shared -fPIC -fno-strict-aliasing -O2 -IpathToYourJavaSDK/include/ -IpathToYourJavaSDK/linux -I../src/  -L../src libwebp_java_wrap.c -lwebp -o libwebp_jni.so    

Loading and managing the native library stuff at runtime is handled by [native-lib-loader](https://github.com/scijava/native-lib-loader).
For more informations behind it and the required folder structure see the [readme](https://github.com/scijava/native-lib-loader/blob/master/README.md) of that project

## Java JNI bindings
The origin libwebp source includes a precompiled jar file which contains the native JNI bindings for libwebp library. 
It is referenced in central pom.xml file of sprd-webp project as 'com.google.webp|webp'

Install this jar library into your local (or company) maven repository

      mvn install:install-file -Dfile=libwebp.jar -DgroupId=com.google.webp -DartifactId=webp -Dversion=5.0.0 -Dpackaging=jar




