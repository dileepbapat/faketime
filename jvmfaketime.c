#include <jni.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <sys/time.h>


int64_t __offsetTime__ = 0;

JNIEXPORT jlong JNICALL Fake_CurrentTimeMillis(JNIEnv* env, jclass jc) {
	struct timeval now;
        gettimeofday(&now, NULL);
	return now.tv_sec * 1000LL + __offsetTime__;
}

static JNINativeMethod fake_method[] = {
    {"currentTimeMillis", "()J", (void*) &Fake_CurrentTimeMillis},
};

JNIEXPORT void JNICALL Java_java_lang_System_setTimeOffset  (JNIEnv *env, jclass cls, jlong value) {
    __offsetTime__ = value; 
}

JNIEXPORT jlong JNICALL Java_java_lang_System_getTimeOffset(JNIEnv *env, jclass cls) {
    return __offsetTime__;
}

JNIEXPORT void JNICALL Java_java_lang_System_registerFakeCurrentTimeMillis(JNIEnv *env, jclass cls) {
    (*env)->RegisterNatives(env, cls, fake_method, sizeof(fake_method) / sizeof(fake_method[0]));
}

JNIEXPORT jlong JNICALL Fake_TrueCurrentTimeMillis(JNIEnv* env, jclass jc) {
	struct timeval now;
	gettimeofday(&now, NULL);
	return now.tv_sec * 1000LL;
}

static JNINativeMethod jdk_method[] = {
    {"currentTimeMillis", "()J", (void*) &Fake_TrueCurrentTimeMillis},
};

JNIEXPORT void JNICALL Java_java_lang_System_deregisterFakeCurrentTimeMillis(JNIEnv *env, jclass cls) {
    (*env)->RegisterNatives(env, cls, jdk_method, sizeof(jdk_method) / sizeof(jdk_method[0]));
}
