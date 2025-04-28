<template>
    <div class="w-full py-5 px-2 mb-3 bg-white border border-gray-200 rounded-lg dark:bg-gray-800 dark:border-gray-700">
        <div class="flex flex-col items-center">
            <!-- 博主头像 -->
            <div class="relative mb-4">
                <img class="w-24 h-24 rounded-full shadow" :src="blogSettingsStore.blogSettings.avatar" />
                <span
                    class="top-0 left-16 absolute  w-3.5 h-3.5 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full"></span>
            </div>
            <!-- 博主昵称 -->
            <h5 class="mb-2 text-xl font-medium text-gray-900 dark:text-white">{{ blogSettingsStore.blogSettings.author }}
            </h5>
            <!-- 介绍语 -->
            <span class="mb-4 text-sm text-gray-500 dark:text-gray-400" data-tooltip-target="introduction-tooltip-bottom"
                data-tooltip-placement="bottom">{{ blogSettingsStore.blogSettings.introduction }}</span>

            <div id="introduction-tooltip-bottom" role="tooltip"
                class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                介绍语
                <div class="tooltip-arrow" data-popper-arrow></div>
            </div>
            			<!-- 文章数量、分类数量、标签数量、总访问量 -->
                        <div class="flex justify-center gap-5 mb-2">
                <div @click="router.push('/archive/list')"
                    class="flex items-center flex-col gap-1 hover:text-blue-700 hover:scale-110 cursor-pointer">
                    <CountTo :value="statisticsInfo.articleTotalCount" customClass="text-lg font-bold"></CountTo>
                    <div class="text-sm">文章</div>
                </div>
                <div @click="router.push('/category/list')"
                    class="flex items-center flex-col gap-1 hover:text-blue-700 hover:scale-110 cursor-pointer">
                    <CountTo :value="statisticsInfo.categoryTotalCount" customClass="text-lg font-bold"></CountTo>
                    <div class="text-sm">分类</div>
                </div>
                <div @click="router.push('/tag/list')"
                    class="flex items-center flex-col gap-1 hover:text-blue-700 hover:scale-110 cursor-pointer">
                    <CountTo :value="statisticsInfo.tagTotalCount" customClass="text-lg font-bold"></CountTo>
                    <div class="text-sm">标签</div>
                </div>
                <div class="flex items-center flex-col gap-1">
                    <CountTo :value="statisticsInfo.pvTotalCount" customClass="text-lg font-bold"></CountTo>
                    <div class="text-sm">总访问量</div>
                </div>
            </div>
            <!-- 第三方平台主页跳转（如 GitHub 等） -->
            <div class="flex justify-center gap-2">
                <!-- GitHub -->
                <svg v-if="blogSettingsStore.blogSettings.githubHomepage" data-tooltip-target="github-tooltip-bottom"
                    data-tooltip-placement="bottom" @click="toOtherWebsite('github')" t="1709025799996"
                    class="hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 outline-none" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="13460" width="200" height="200">
                    <path
                        d="M512 12.63616c-282.74688 0-512 229.21216-512 512 0 226.22208 146.69824 418.14016 350.12608 485.82656 25.57952 4.73088 35.00032-11.10016 35.00032-24.63744 0-12.20608-0.47104-52.55168-0.69632-95.31392-142.4384 30.96576-172.50304-60.416-172.50304-60.416-23.28576-59.16672-56.85248-74.91584-56.85248-74.91584-46.44864-31.78496 3.50208-31.1296 3.50208-31.1296 51.4048 3.60448 78.47936 52.75648 78.47936 52.75648 45.6704 78.27456 119.76704 55.64416 149.01248 42.55744 4.58752-33.09568 17.85856-55.68512 32.50176-68.46464-113.72544-12.94336-233.2672-56.85248-233.2672-253.0304 0-55.88992 20.00896-101.5808 52.75648-137.4208-5.3248-12.9024-22.85568-64.96256 4.95616-135.49568 0 0 43.008-13.74208 140.84096 52.49024 40.83712-11.34592 84.64384-17.03936 128.16384-17.24416 43.49952 0.2048 87.32672 5.87776 128.24576 17.24416 97.73056-66.2528 140.65664-52.49024 140.65664-52.49024 27.87328 70.53312 10.3424 122.59328 5.03808 135.49568 32.82944 35.86048 52.69504 81.53088 52.69504 137.4208 0 196.64896-119.78752 239.94368-233.79968 252.6208 18.37056 15.89248 34.73408 47.04256 34.73408 94.80192 0 68.5056-0.59392 123.63776-0.59392 140.51328 0 13.6192 9.216 29.5936 35.16416 24.576 203.32544-67.76832 349.83936-259.62496 349.83936-485.76512 0-282.78784-229.23264-512-512-512z"
                        fill="#1296db" p-id="13461"></path>
                </svg>
                <div id="github-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    我的 GitHub
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>
                <!-- Gitee -->
                <svg v-if="blogSettingsStore.blogSettings.giteeHomepage" data-tooltip-target="gitee-tooltip-bottom"
                    data-tooltip-placement="bottom" @click="toOtherWebsite('gitee')" t="1698030969736"
                    class="hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 outline-none" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2438" width="200" height="200">
                    <path
                        d="M512 1024C229.222 1024 0 794.778 0 512S229.222 0 512 0s512 229.222 512 512-229.222 512-512 512z m259.149-568.883h-290.74a25.293 25.293 0 0 0-25.292 25.293l-0.026 63.206c0 13.952 11.315 25.293 25.267 25.293h177.024c13.978 0 25.293 11.315 25.293 25.267v12.646a75.853 75.853 0 0 1-75.853 75.853h-240.23a25.293 25.293 0 0 1-25.267-25.293V417.203a75.853 75.853 0 0 1 75.827-75.853h353.946a25.293 25.293 0 0 0 25.267-25.292l0.077-63.207a25.293 25.293 0 0 0-25.268-25.293H417.152a189.62 189.62 0 0 0-189.62 189.645V771.15c0 13.977 11.316 25.293 25.294 25.293h372.94a170.65 170.65 0 0 0 170.65-170.65V480.384a25.293 25.293 0 0 0-25.293-25.267z"
                        fill="#C71D23" p-id="2439"></path>
                </svg>
                <div id="gitee-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    我的 Gitee
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>
                <!-- 知乎 -->
                <svg v-if="blogSettingsStore.blogSettings.zhihuHomepage" data-tooltip-target="zhihu-tooltip-bottom"
                    data-tooltip-placement="bottom" @click="toOtherWebsite('zhihu')" t="1709026704156"
                    class="hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 outline-none" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="20349" width="200" height="200">
                    <path
                        d="M512 1024C229.236 1024 0 794.764 0 512S229.236 0 512 0s512 229.236 512 512-229.236 512-512 512zM382.138 267.52s-36.212 2.095-48.989 24.483c-12.8 22.365-54.318 137.379-54.318 137.379s13.847 6.377 37.282-10.66c23.436-17.035 30.883-46.847 30.883-46.847l42.59-2.118 1.07 121.39s-73.495-1.07-88.413 0c-14.895 1.048-23.412 40.449-23.412 40.449h111.825s-9.588 67.095-38.353 116.084c-28.742 48.99-83.06 87.32-83.06 87.32s39.423 15.964 77.73-6.4c38.354-22.343 66.63-120.693 66.63-120.693l89.926 110.057s8.192-52.387-1.466-67.189c-9.659-14.778-62.208-74.286-62.208-74.286l-22.947 20.247 16.337-65.117H531.2s0-38.354-19.153-40.495c-19.177-2.094-78.802 0-78.802 0V371.898h88.39s-1.07-39.4-18.106-39.4H359.773l22.342-64.955z m169.984 61.184v358.563h36.003l13.103 45.01 63.348-45.01h89.065V328.704H552.122z"
                        fill="#1296db" p-id="20350"></path>
                    <path d="M594.781 368.64h117.9v277.876H670.79l-53.365 40.262-11.636-40.262h-11.008V368.64z"
                        fill="#1296db" p-id="20351"></path>
                </svg>
                <div id="zhihu-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    我的知乎
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>
                <!-- CSDN -->
                <svg v-if="blogSettingsStore.blogSettings.csdnHomepage" data-tooltip-target="csdn-tooltip-bottom"
                    data-tooltip-placement="bottom" @click="toOtherWebsite('csdn')" t="1698031311586"
                    class="hover:scale-110 cursor-pointer icon mt-5 w-7 h-7 outline-none" viewBox="0 0 1024 1024"
                    version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4386" width="200" height="200">
                    <path
                        d="M512 1024C229.2224 1024 0 794.7776 0 512 0 229.2224 229.2224 0 512 0c282.7776 0 512 229.2224 512 512 0 282.7776-229.2224 512-512 512z m17.066667-413.525333c34.850133 4.352 68.778667 5.12 102.741333 2.0992 23.04-2.048 44.817067-8.362667 64.170667-21.9136 38.212267-26.794667 49.783467-85.1968 24.251733-123.050667-14.626133-21.7088-36.8128-30.344533-60.757333-35.498667-35.054933-7.543467-70.4512-5.751467-105.847467-3.413333-5.666133 0.3584-6.7584 3.072-7.236267 8.209067-3.072 32.682667-6.536533 65.314133-9.813333 97.962666-2.5088 24.814933-4.932267 49.629867-7.509333 75.605334z m53.4016-33.928534c1.962667-20.906667 3.6352-39.338667 5.4272-57.770666 1.553067-15.906133 3.413333-31.778133 4.727466-47.701334 0.3584-4.283733 1.553067-6.656 5.956267-6.382933 15.616 1.041067 31.709867 0.034133 46.728533 3.652267 36.488533 8.823467 48.725333 54.306133 23.3472 83.029333-15.8208 17.902933-36.7616 23.586133-59.255466 25.088-8.465067 0.546133-17.015467 0.085333-26.9312 0.085333zM512 434.295467c-2.184533-0.648533-3.5328-1.1776-4.932267-1.4336-37.717333-6.877867-75.690667-8.328533-113.646933-2.816-20.974933 3.037867-41.0112 9.489067-57.480533 23.330133-22.9888 19.319467-21.640533 46.848 4.4032 62.0032 13.056 7.594667 28.023467 12.509867 42.5984 17.288533 14.08 4.608 28.996267 6.826667 43.144533 11.264 12.5952 3.925333 14.011733 14.318933 3.584 22.306134-3.345067 2.56-7.441067 5.085867-11.537067 5.751466-11.195733 1.826133-22.698667 4.386133-33.826133 3.566934-24.098133-1.774933-48.042667-5.461333-72.5504-8.430934-1.365333 10.615467-2.935467 23.0912-4.5568 35.9424 4.181333 1.365333 7.68 2.730667 11.264 3.618134 33.9456 8.4992 68.386133 9.608533 102.912 5.12 20.087467-2.6112 39.4752-7.901867 56.695467-19.029334 28.603733-18.4832 36.693333-57.1904-4.676267-75.383466-14.506667-6.382933-30.190933-10.410667-45.482667-15.086934-11.4176-3.4816-23.313067-5.614933-34.525866-9.5232-9.7792-3.413333-11.144533-12.202667-3.037867-18.397866 4.6592-3.549867 10.717867-6.997333 16.384-7.3728a480.853333 480.853333 0 0 1 53.384533-0.853334c15.377067 0.699733 30.651733 3.549867 46.4896 5.5296L512 434.295467z m257.143467 2.048L750.933333 614.2976h54.152534c4.778667-45.636267 9.710933-90.7264 14.062933-135.8848 0.6144-6.365867 2.3552-8.840533 8.686933-9.0112 11.434667-0.273067 22.8864-1.979733 34.286934-1.570133 23.722667 0.853333 42.3936 9.728 38.4 43.264-2.901333 24.2688-5.597867 48.571733-8.2432 72.874666-1.092267 10.069333-1.826133 20.189867-2.730667 30.4128h55.330133c3.584-35.259733 7.9872-70.058667 10.496-104.994133 3.413333-47.4624-17.7664-73.3184-64.682666-80.213333-40.96-6.007467-81.339733-0.341333-121.5488 7.133866z m-483.498667 134.6048c-8.738133 1.297067-16.384 2.798933-24.098133 3.4816-25.6512 2.235733-51.319467 3.9424-76.305067-4.266667-13.909333-4.590933-24.6784-12.578133-29.7984-25.9584-7.901867-20.701867 0.887467-47.104 19.831467-60.3136 17.373867-12.117333 37.717333-15.9232 58.453333-15.9232 22.545067-0.017067 45.090133 2.423467 68.232533 3.84L307.2 432.298667c-15.069867-1.723733-29.4912-3.925333-43.997867-4.9152-41.0112-2.798933-80.64 2.6112-117.469866 20.462933-30.020267 14.557867-52.053333 36.010667-58.6752 68.130133-7.850667 38.144 11.537067 69.495467 51.7632 85.845334 19.1488 7.765333 39.287467 12.509867 60.0064 12.5952 24.746667 0.1024 49.493333-1.570133 74.205866-2.952534 3.106133-0.170667 8.311467-2.901333 8.669867-5.034666 1.979733-11.554133 2.730667-23.278933 3.9424-35.464534z"
                        fill="#DD1700" p-id="4387"></path>
                </svg>
                <div id="csdn-tooltip-bottom" role="tooltip"
                    class="absolute z-10 invisible inline-block px-3 py-2 text-xs font-medium text-white bg-gray-900 rounded shadow-sm opacity-0 tooltip dark:bg-gray-700">
                    我的 CSDN
                    <div class="tooltip-arrow" data-popper-arrow></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useBlogSettingsStore } from '@/stores/blogsettings'
import { initTooltips } from 'flowbite'
import { ref, onMounted } from 'vue'
import { getStatisticsInfo } from '@/api/frontend/statistics'
import CountTo from '@/components/CountTo.vue'

// 初始化 Flowbit 组件
onMounted(() => {
    initTooltips();
})

// 引入博客设置信息 store
const blogSettingsStore = useBlogSettingsStore()

// 网页跳转
const toOtherWebsite = (path) => {
    if (path === "github") {
        window.open(blogSettingsStore.blogSettings.githubHomepage, '_blank');
    } else if (path === "gitee") {
        window.open(blogSettingsStore.blogSettings.giteeHomepage, '_blank');
    } else if (path === "zhihu") {
        window.open(blogSettingsStore.blogSettings.zhihuHomepage, '_blank');
    } else if (path === "csdn") {
        window.open(blogSettingsStore.blogSettings.csdnHomepage, '_blank');
    }
}

// 统计信息(文章、分类、标签数量、总访问量)
const statisticsInfo = ref({})
getStatisticsInfo().then(res => {
    if (res.success) {
        statisticsInfo.value = res.data
    }
})

</script>