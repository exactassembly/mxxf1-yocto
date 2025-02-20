FILESEXTRAPATHS:prepend := "${THISDIR}/files-u-boot-fslc:"
SRC_URI:append = " \
	file://01-remove-networking.cfg \
    "