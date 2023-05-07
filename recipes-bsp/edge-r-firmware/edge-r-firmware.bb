SUMMARY = "ASUS Tinker Board Edge R Firmware"
DESCRIPTION = "Binary firmware blobs to support ASUS Tinker Board Edge R components."
SECTION = "base"
LICENSE = "CLOSED"
PR = "r1"

SRC_URI = "file://rtl8822bu_config \
           file://rtl8822bu_fw \
           file://rtl8822cu_config \
           file://rtl8822cu_fw \
           "

S = "${WORKDIR}"

do_install () {
    install -m 0755 -d ${D}/lib
    install -m 0755 -d ${D}/lib/firmware
    install -m 0755 ${WORKDIR}/rtl8822bu_config ${D}/lib/firmware/rtl8822bu_config
    install -m 0755 ${WORKDIR}/rtl8822bu_fw ${D}/lib/firmware/rtl8822bu_fw
    install -m 0755 ${WORKDIR}/rtl8822cu_config ${D}/lib/firmware/rtl8822cu_config
    install -m 0755 ${WORKDIR}/rtl8822cu_fw ${D}/lib/firmware/rtl8822cu_fw
}

FILES:${PN} = "/lib/firmware/*"

