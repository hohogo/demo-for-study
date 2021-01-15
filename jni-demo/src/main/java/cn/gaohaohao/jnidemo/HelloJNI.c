#include<jni.h>
#include <stdio.h>
#include "cn_gaohaohao_jnidemo_HelloJNI.h"

JNIEXPORT void JNICALL Java_cn_gaohaohao_jnidemo_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Hello World!\n");
   return;
}