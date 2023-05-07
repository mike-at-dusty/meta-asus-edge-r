# fancontrol.bb
SUMMARY = "This is a simple script and service that allows the monitoring of the SoC temperature regularly and then control the fan to be high or low speed automatically through the GPIO."
HOMEPAGE = "https://gist.github.com/FrankWu100/9b7dee45ce40729221cb277bce1102d9"
SECTION = "utility"
LICENSE = "CLOSED"

COMPATIBLE_HOST = "aarch64.*-linux"

RDEPENDS:${PN} += "bash"

SRC_URI = "file://fancontrol.sh \
           file://fancontrol.service \
           "

S = "${WORKDIR}"

inherit systemd

do_install() {
    install -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/fancontrol.sh ${D}/${bindir}/fancontrol

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/fancontrol.service ${D}${systemd_unitdir}/system/fancontrol.service

        install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants/
        ln -s ${systemd_unitdir}/system/fancontrol.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/fancontrol.service
    fi
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "fancontrol.service"
SYSTEMD_AUTO_ENABLE = "enable"
