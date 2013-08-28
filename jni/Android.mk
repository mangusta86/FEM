LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := solver
LOCAL_SRC_FILES := solver.cpp

include $(BUILD_SHARED_LIBRARY)
