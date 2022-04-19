DESCRIPTION = "Linux kernel for ASUS Tinker Board Edge R"

require recipes-kernel/linux/linux-yocto.inc
require recipes-kernel/linux/linux-rockchip.inc

inherit freeze-rev local-git

SRCREV = "ab830709a6ff8a36a7bb1defd96ae19c14d78108"
SRC_URI = " \
  git://github.com/TinkerEdgeR/debian-kernel.git;branch=linux4.4-rk3399pro \
  file://${THISDIR}/files/tinker_board_edger.cfg \
  file://${THISDIR}/files/0001-Fixed-linking-error-with-yylloc.patch \
"


KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION = "4.4.194"

