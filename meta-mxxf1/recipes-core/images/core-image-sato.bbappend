ROOTFS_POSTPROCESS_COMMAND += " image_add_bootables;"

image_add_bootables() {
    install -m 0755 ${DEPLOY_DIR_IMAGE}/zImage ${IMAGE_ROOTFS}/boot/   
    install -m 0755 ${DEPLOY_DIR_IMAGE}/mxxf1-hw4.dtb ${IMAGE_ROOTFS}/boot/
}
