SUMMARY = "Minimal image for ASUS Tinker Board Edge R"

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image features_check extrausers

REQUIRED_DISTRO_FEATURES = "pam systemd"

CORE_IMAGE_BASE_INSTALL += " \
    kernel-modules \
"

