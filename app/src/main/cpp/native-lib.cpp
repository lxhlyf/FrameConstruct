#include <jni.h>
#include "com_frameconstruct_ndk_NdkMethod.h"
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_frameconstruct_ndk_NdkMethod_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++ lxh";
    return env->NewStringUTF(hello.c_str());
}
