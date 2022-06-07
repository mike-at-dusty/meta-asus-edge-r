FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Add-target-to-generate-initial-environment.patch \
            file://0002-Modified-Android-boot-partitions.patch \
            file://0003-Fixed-linking-error-with-yylloc.patch \
            file://0004-Removed-troublesome-dtb-files.patch \
            "

