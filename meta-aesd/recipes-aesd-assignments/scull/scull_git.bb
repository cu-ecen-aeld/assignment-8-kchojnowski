LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module
inherit update-rc.d

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-kchojnowski.git;protocol=ssh;branch=main \
           file://0001-include-only-misc-modules-and-scull-subdirs.patch \
           file://scull-load \
           "
PV = "1.0+git${SRCPV}"
SRCREV = "46445b1d6a5ec9ca3bb21e757a9221c2662fb053"

S = "${WORKDIR}/git"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "scull-load"

do_install:append() {
  install -d ${D}${sysconfdir}/init.d
  install -m 0755 ${WORKDIR}/scull-load ${D}${sysconfdir}/init.d
}

FILES:${PN} += "${sysconfdir}/init.d/scull-load"
