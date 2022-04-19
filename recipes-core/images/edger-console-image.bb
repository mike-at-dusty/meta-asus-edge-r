require edger-minimal-image.bb

SUMMARY = "Basic console image for ASUS Tinker Board Edge R"

IMAGE_FEATURES += "package-management ssh-server-openssh"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-edger-console \
"

