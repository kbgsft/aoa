LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := native_lib
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	D:\Project\aoa\app\src\main\jni\Android.mk \
	D:\Project\aoa\app\src\main\jni\Application.mk \
	D:\Project\aoa\app\src\main\jni\native_lib.c \

LOCAL_C_INCLUDES += D:\Project\aoa\app\src\main\jni
LOCAL_C_INCLUDES += D:\Project\aoa\app\src\debug\jni

include $(BUILD_SHARED_LIBRARY)
