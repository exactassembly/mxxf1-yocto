DESCRIPTION = "Package group for ThrustmasterTX User Interface"
LICENCE = "CLOSED"
SUMMARY = "This package group adds the ThrustmasterTX GUI and dependencies to the image"
inherit packagegroup

RDEPENDS:${PN} = "\
    tmtx-ui \
"