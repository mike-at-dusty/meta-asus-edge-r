inherit freeze-rev local-git

SRCREV = "6eabb05744a20b5d9083ec29abc5947532134e22"
SRCREV_rkbin = "104659686b734ab041ef958c0abece1a250f48a4"
SRC_URI = " \
	git://github.com/TinkerEdgeR/debian-u-boot.git;protocol=https;branch=linux4.4-rk3399pro; \
	git://github.com/JeffyCN/mirrors.git;protocol=https;branch=rkbin;name=rkbin;destsuffix=rkbin; \
"
