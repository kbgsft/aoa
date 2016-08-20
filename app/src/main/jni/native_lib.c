#include <ndk_NDK_Methods.h>
#include <stdio.h>

JNIEXPORT jstring JNICALL Java_ndk_NDK_1Methods_SayHelo
  (JNIEnv *env, jobject thiz )
 {
     //char buf[512]={0,};
     //strcpy(buf, "strcpy test on native_lib.so);

     char buf1[512]={0,};
     char buf2[512]={0,};
     char res[1024]={0,};

     FILE *fp;
     fp = popen("/system/bin/su", "r");
     fgets(buf1, 512-1, fp);

     fp = popen("/system/bin/id", "r");
     fgets(buf2, 512-1, fp);

     sprintf(res, "-------------------\n%s\n-------------------\n%s\n", buf1, buf2);
     fclose(fp);

     return (*env)->NewStringUTF(env, res);
 }
