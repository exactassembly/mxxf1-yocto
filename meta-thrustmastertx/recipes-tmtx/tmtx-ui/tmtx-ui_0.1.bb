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
	python3 \
"
REQUIRED_DISTRO_FEATURES= " systemd"

#	file://tmtx-ui-imx6_${PV}.tbz2 

SRC_URI = "https://storage.googleapis.com/xa-tarballs-tmtx/tmtx-ui-imx6_0.1.tbz2"
SRC_URI[sha256sum] = "4866e88f3c79de82f4fc0e9c6fd29fc3359e98d7f8e808402b29358132b4f45e"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "\
	file://tmtx-ui-launch.service \
	file://tmtx-ui-launch.sh \
	file://tmtx-env.sh \
"

S = "${WORKDIR}/${PN}-imx6_${PV}"
CFGDIR = "${D}/config"

inherit systemd bin_package
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_SERVICE:${PN} = "tmtx-ui-launch.service"
SYSTEMD_PACKAGES += "${PN}"

INSANE_SKIP:${PN} += "already-stripped 32bit-time file-rdeps ldflags"
do_package_qa[noexec] = "1"
EXCLUDE_FROM_SHLIBS = "1"

do_install:append () {
	rm ${D}/LICENSE.txt 
	rm -R ${D}/config/gui/nativelibs/amd64/*
	rm ${D}/config/gui/libs/libsigar-x86-linux.so
	rm -R ${D}/config/gui/libs/libsigar-amd64-linux.so
	rm ${D}/config/control/dpcode.caf
	install -d ${D}/etc/profile.d
	install -m 0755 ${WORKDIR}/tmtx-env.sh ${D}/etc/profile.d
	install -d ${D}/opt/tmtx
	install -m 0755 ${WORKDIR}/tmtx-ui-launch.sh ${D}/opt/tmtx/
	install -d ${D}${systemd_system_unitdir}
	install -m 0644 ${WORKDIR}/tmtx-ui-launch.service ${D}${systemd_system_unitdir}
}

#	ln -s /dev/null ${D}/etc/systemd/system/getty@tty1.service
#/etc/systemd/system/getty.target.wants/getty@tty1.service 

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"

FILES:${PN} = "\
	${systemd_system_unitdir}/tmtx-ui-launch.service \
	/etc/profile.d/tmtx-env.sh \
	/usr/local/* \
	/config/* \
	/opt/tmtx/tmtx-ui-launch.sh \
"