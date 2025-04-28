<template>
    <!-- 固钉组件，通过设置 offset 属性来改变吸顶距离，默认值为 0。 -->
    <el-affix :offset="0">
        <!-- 设置背景色为白色、高度为 64px，padding-right 为 4， border-bottom 为 slate 100 -->
        <div class="bg-white h-[64px] flex pr-4 border-b border-slate-100">
            <!-- 左边栏收缩、展开 -->
            <div class="w-[42px] h-[64px] cursor-pointer flex items-center justify-center text-gray-700 hover:bg-gray-200"
                @click="handleMenuWidth">
                <el-icon>
                    <Fold v-if="menuStore.menuWidth == '250px'" />
                    <Expand v-else />
                </el-icon>
            </div>

            <!-- 右边容器，通过 ml-auto 让其在父容器的右边 -->
            <div class="ml-auto flex">
                <!-- 点击刷新页面 -->
                <el-tooltip class="box-item" effect="dark" content="刷新" placement="bottom">
                    <div class="w-[42px] h-[64px] cursor-pointer flex items-center justify-center text-gray-700 hover:bg-gray-200"
                        @click="handleRefresh">
                        <el-icon>
                            <Refresh />
                        </el-icon>
                    </div>
                </el-tooltip>
                <!-- 点击跳转前台首页 -->
                <el-tooltip class="box-item" effect="dark" content="跳转前台" placement="bottom">
                    <div class="w-[42px] h-[64px] cursor-pointer flex items-center justify-center text-gary-700 hover:bg-gray-200"
                        @click="router.push('/')">
                        <el-icon>
                            <Monitor />
                        </el-icon>
                    </div>
                </el-tooltip>
                <!-- 点击全屏展示 -->
                <el-tooltip class="box-item" effect="dark" content="全屏" placement="bottom">
                    <div class="w-[42px] h-[64px] cursor-pointer flex items-center justify-center text-gray-700 mr-2 hover:bg-gray-200"
                        @click="toggle">
                        <el-icon>
                            <FullScreen v-if="!isFullscreen" />
                            <Aim v-else />
                        </el-icon>
                    </div>
                </el-tooltip>
                <!-- 登录用户头像 -->
                <el-dropdown class="flex items-center justify-center" @command="handleCommand">
                    <span class="el-dropdown-link flex items-center justify-center text-gray-700 text-xs">
                        <!-- 头像 Avatar -->
                        <el-avatar class="mr-2" :size="25"
                            src="https://img.howonenew.com/weblog/1.jpg" />
                        {{ userStore.userInfo.username }}
                        <el-icon class="el-icon--right">
                            <arrow-down />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="updatePassword">修改密码</el-dropdown-item>
                            <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
                <!-- 修改密码 -->
                <el-dialog v-model="dialogVisible" title="修改密码" width="40%" :draggable="true" :close-on-click-modal="false"
                    :close-on-press-escape="false">
                    <span>This is a message</span>
                    <template #footer>
                        <span class="dialog-footer">
                            <el-button @click="dialogVisible = false">取消</el-button>
                            <el-button type="primary" @click="dialogVisible = false">
                                提交
                            </el-button>
                        </span>
                    </template>
                </el-dialog>
            </div>
        </div>
    </el-affix>
</template>

<script setup>
import { ref } from 'vue'
import { useMenuStore } from '@/stores/menu'
import { useFullscreen } from '@vueuse/core'
import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router'
import { showModel, showMessage } from '@/composables/util'

//引入了用户 Store
const userStore = useUserStore()
const router = useRouter()

// 对话框是否显示
const dialogVisible = ref(false)
// 下拉菜单事件处理
const handleCommand = (command) => {
    // 更新密码
    if (command === 'updatePassword') {
        // 显示修改密码对话框
        dialogVisible.value = true
    } else if (command === 'logout') { // 退出登录
        logout()
    }
}

// 退出登录
function logout() {
    showModel('是否确认要退出登录？').then(() => {
        userStore.logout()
        showMessage('退出登录成功！')
        // 跳转登录页
        router.push('/login')
    })
}
//引入了菜单store
const menuStore = useMenuStore()
//icon 点击事件
const handleMenuWidth = () => {
    //动态设置菜单的宽度大小
    menuStore.handleMenuWidth()
}
//isDullScreen 表示当前是否处于全屏，toggle用于动态切换全屏、非全屏
const { isFullscreen, toggle } = useFullscreen()
//刷新页面
const handleRefresh = () => location.reload()
</script>
