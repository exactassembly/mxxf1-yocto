FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
	file://0009_netfilter.cfg \
	file://0008_tweaks.cfg \
	file://0007_intel_e1000.cfg \
"
