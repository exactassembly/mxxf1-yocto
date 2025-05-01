# Recipe created by recipetool
DESCRIPTION = "DataRespons MxxF1-10.4 Panel PC DTB"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "dr-imx6-mc"

SRC_URI = "https://storage.googleapis.com/xa-tarballs-tmtx/mxxf1-hw4.dtb"
SRC_URI[sha256sum] = "b8d19b6d7870fed8fbdd03b28cc1e11b7407802c03049a5646bff6b4f15613e1"

S="${WORKDIR}"

EXCLUDE_FROM_SHLIBS = "1"

do_install:append () {
	install -d ${D}/boot
	install -m 0444 ${S}/mxxf1-hw4.dtb ${D}/boot
}

do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_package_qa[noexec] = "1"

FILES:${PN} = "\
    /boot/mxxf1-hw4.dtb \
"