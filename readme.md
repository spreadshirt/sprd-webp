# sprd-webp
sprd-webp is a java wrapper around googles libwebp for generating webp encoded images.It is based on the the great [webp 
project of Luciad](https://bitbucket.org/luciad/webp-imageio). But it uses a newer version of 
[googles libwebp library](https://developers.google.com/speed/webp/docs/api) (>0.4.5) 
and provides only the simple API of the google webp library. 


# Prerequirements


## Google WebP library
Use the shipped native libwebp libraries (src/main/resources/META-INF/lib) or download the latest source from
[https://developers.google.com/speed/webp/](https://developers.google.com/speed/webp/docs/precompiled#getting_cwebp_dwebp_and_the_webp_libraries).
The only thing to do is: put the library for your system into the META-INF/lib folder and have fun.

Loading and managing the native library stuff at runtime is handled by [native-lib-loader](https://github.com/scijava/native-lib-loader).
For more informations behind it and the required folder structure see the [readme](https://github.com/scijava/native-lib-loader/blob/master/README.md) of that project

## Java JNI bindings
The origin libwebp source includes a precompiled jar file which contains the native JNI bindings for libwebp library. 
It is referenced in central pom.xml file of sprd-webp project as 'com.google.webp|webp'

Install this jar library into your local (or company) maven repository

      mvn install:install-file -Dfile=libwebp.jar -DgroupId=com.google.webp -DartifactId=webp -Dversion=5.0.0 -Dpackaging=jar


# Testing and Examples

You can find a simple example with different compression rates here:

    src/test/java/net/sprd/image/webp/UsageExample.java

# Travis-ci and releases

[see](https://travis-ci.org/spreadshirt/sprd-webp)


