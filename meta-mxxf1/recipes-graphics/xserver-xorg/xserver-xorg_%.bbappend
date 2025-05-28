FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = " file://90-calibration.conf"

do_install:append() {
    install -m 0644 ${WORKDIR}/90-calibration.conf ${D}/usr/share/X11/xorg.conf.d/
}