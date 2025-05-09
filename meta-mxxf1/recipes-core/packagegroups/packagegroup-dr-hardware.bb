SUMMARY = "Standard packages for Data Respons hardware"
LICENSE = "MIT"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup
PROVIDES = "${PACKAGES}"
PACKAGES = "\
	packagegroup-dr-hardware \
	packagegroup-dr-net \
	packagegroup-dr-wifi \
	packagegroup-dr-screen \
	packagegroup-dr-sound \
"


RDEPENDS:packagegroup-dr-hardware = "\
	packagegroup-dr-net \
	${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'packagegroup-dr-screen', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'wifi', 'packagegroup-dr-wifi', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'alsa', 'packagegroup-dr-sound', '',d)} \
"

RDEPENDS:packagegroup-dr-net = "\
	networkmanager \
	networkmanager-nmcli \
	networkmanager-nmcli-bash-completion \
	ethtool \
	netdev-names \
"

RDEPENDS:packagegroup-dr-wifi = "\
	wpa-supplicant \
	wpa-supplicant-cli \
	wpa-supplicant-passphrase \
	iw \
	rfkill \
"

RDEPENDS:packagegroup-dr-screen = "\
	weston \
	weston-init \
"

RDEPENDS:packagegroup-dr-sound = "\
	pulseaudio \
	pulseaudio-server \
	pulseaudio-pa-info \
	pulseaudio-misc \
	alsa-utils \
"