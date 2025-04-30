# Recipe created by recipetool
DESCRIPTION = "ThrustmasterTX GUI"
DEPENDS = "\
	coreutils \
	protobuf \
	boost \
"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=96af5705d6f64a88e035781ef00e98a8"
COMPATIBLE_MACHINE = "dr-imx6-mc"

RDEPENDS:${PN} += "\
	bash \
"

#	file://tmtx-ui-imx6_${PV}.tbz2 

SRC_URI = "https://drive.google.com/file/d/1wbSo3K2RxEjzrUj3hQM4-QZyXPJcnCaF/view?usp=drive_link;downloadfilename=tmtx-ui-imx6_${PV}.tbz2"
SRC_URI[sha256sum] = "38bcca390f14b09bdd3da21ac2c9f25a4f9ad7de3cffe01f353dbed5df06f1e4"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "\
	file://start_dpgui.service \
	file://start_dpgui.sh \
"

S = "${WORKDIR}/${PN}-imx6_${PV}"
CFGDIR = "${D}/config"

inherit bin_package
#inherit systemd bin_package
#SYSTEMD_AUTO_ENABLE = "enable"
#SYSTEMD_SERVICE_${PN} = "start_dpgui.service"

INSANE_SKIP:${PN} = "already-stripped 32bit-time ldflags"

do_install:append () {
	rm ${D}/LICENSE.txt 
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/start_dpgui.service ${D}/${systemd_unitdir}/system
}

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"

FILES:${PN} = "\
	${bindir}/ \
	/usr/lib/systemd/system/start_dpgui.service \
	/config/* \
"