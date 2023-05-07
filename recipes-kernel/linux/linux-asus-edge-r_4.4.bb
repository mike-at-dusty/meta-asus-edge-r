DESCRIPTION = "Linux kernel for ASUS Tinker Board Edge R"

require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/linux-rockchip.inc

inherit local-git

SRCREV = "ab830709a6ff8a36a7bb1defd96ae19c14d78108"
SRC_URI = " \
  git://github.com/TinkerEdgeR/debian-kernel.git;protocol=https;branch=linux4.4-rk3399pro \
  file://${THISDIR}/files/asus_edge_r.cfg \
  file://${THISDIR}/files/0001-Fixed-linking-error-with-yylloc.patch \
"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION = "4.4.194"
