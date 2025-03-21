SUMMARY = "ThrustmasterTX image."
DESCRIPTION = "Image with Sato, a mobile environment and visual style for \
mobile devices. The image supports X11 with a Sato theme, Pimlico \
applications, and contains terminal, editor, and file manager."


IMAGE_FEATURES += "splash package-management x11-base x11-sato ssh-server-dropbear hwcodecs"


LICENSE = "MIT"

inherit core-image

TOOLCHAIN_HOST_TASK:append = " nativesdk-intltool nativesdk-glib-2.0"
TOOLCHAIN_HOST_TASK:remove:task-populate-sdk-ext = " nativesdk-intltool nativesdk-glib-2.0"

QB_MEM = '${@bb.utils.contains("DISTRO_FEATURES", "opengl", "-m 512", "-m 256", d)}'
QB_MEM:qemuarmv5 = "-m 256"
QB_MEM:qemumips = "-m 256"

ROOTFS_POSTPROCESS_COMMAND += " image_add_bootables;"

image_add_bootables() {
    install -m 0755 ${DEPLOY_DIR_IMAGE}/zImage ${IMAGE_ROOTFS}/boot/   
    install -m 0755 ${DEPLOY_DIR_IMAGE}/mxxf1-hw4.dtb ${IMAGE_ROOTFS}/boot/
}
