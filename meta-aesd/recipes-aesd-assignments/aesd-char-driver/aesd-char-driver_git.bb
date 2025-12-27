LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module
inherit update-rc.d

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-kchojnowski.git;protocol=ssh;branch=main \
           file://aesdchar-load \
           "
PV = "1.0+git${SRCPV}"
SRCREV = "037b09be8f011eacaf6311c76a324092c098230f"

S = "${WORKDIR}/git/aesd-char-driver"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdchar-load"

do_install:append() {
  install -d ${D}${sysconfdir}/init.d
  install -m 0755 ${WORKDIR}/aesdchar-load ${D}${sysconfdir}/init.d
}

FILES:${PN} += "${sysconfdir}/init.d/aesdchar-load"

