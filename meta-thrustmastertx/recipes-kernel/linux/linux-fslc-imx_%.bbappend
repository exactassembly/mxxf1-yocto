FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
	file://0009_netfilter.cfg \
	file://0008_tweaks.cfg \
"
