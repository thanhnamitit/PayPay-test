#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_paypay_test_di_module_NetworkModule_accessKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string value = "2ef4d2b9e80274f56ec4abe547f40c11";
    return env->NewStringUTF(value.c_str());
}
