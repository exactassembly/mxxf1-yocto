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

SRC_URI = "https://storage.googleapis.com/xa-tarballs-tmtx/tmtx-ui-imx6_0.1.tbz2"
SRC_URI[sha256sum] = "4866e88f3c79de82f4fc0e9c6fd29fc3359e98d7f8e808402b29358132b4f45e"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "\
	file://start_dpgui.service \
	file://tmtx-env.sh \
"

S = "${WORKDIR}/${PN}-imx6_${PV}"
CFGDIR = "${D}/config"

inherit bin_package
#inherit systemd bin_package
#SYSTEMD_AUTO_ENABLE = "enable"
#SYSTEMD_SERVICE_${PN} = "start_dpgui.service"

INSANE_SKIP:${PN} += "already-stripped 32bit-time file-rdeps ldflags"
do_package_qa[noexec] = "1"
EXCLUDE_FROM_SHLIBS = "1"

do_install:append () {
	rm ${D}/LICENSE.txt 
	rm -R ${D}/config/gui/nativelibs/amd64/*
	rm ${D}/config/gui/libs/libsigar-x86-linux.so
	rm -R ${D}/config/gui/libs/libsigar-amd64-linux.so
	install -d ${D}/etc/profile.d
	install -m 0444 ${WORKDIR}/tmtx-env.sh ${D}/etc/profile.d
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/start_dpgui.service ${D}/${systemd_unitdir}/system
}

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"

FILES:${PN} = "\
	/usr/lib/systemd/system/start_dpgui.service \
	/etc/profile.d/tmtx-env.sh \
	/usr/local/* \
	/config/* \
"