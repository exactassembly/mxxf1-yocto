SUMMARY = "ThrustmasterTX image."
DESCRIPTION = "PanelPC appliance running ThrustmasterTX control GUI"

IMAGE_FEATURES += "package-management ssh-server-dropbear hwcodecs weston"

IMAGE_INSTALL += " packagegroup-tmtx-ui weston-xwayland matchbox-terminal"

LICENSE = "MIT"

inherit core-image

TOOLCHAIN_HOST_TASK:append = " nativesdk-intltool nativesdk-glib-2.0"
TOOLCHAIN_HOST_TASK:remove:task-populate-sdk-ext = " nativesdk-intltool nativesdk-glib-2.0"

QB_MEM = '${@bb.utils.contains("DISTRO_FEATURES", "opengl", "-m 512", "-m 256", d)}'
QB_MEM:qemuarmv5 = "-m 256"
QB_MEM:qemumips = "-m 256"

IMAGE_ROOTFS_EXTRA_SPACE = "4194304"

ROOTFS_POSTPROCESS_COMMAND += " image_add_bootables;"

image_add_bootables() {
    install -m 0755 ${DEPLOY_DIR_IMAGE}/zImage ${IMAGE_ROOTFS}/boot/   
    install -m 0755 ${DEPLOY_DIR_IMAGE}/mxxf1-hw4.dtb ${IMAGE_ROOTFS}/boot/
}
