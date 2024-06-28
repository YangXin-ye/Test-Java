<template>
    <div>
        <el-card class="box-card">

            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="搜索信息">
                    <el-input placeholder="请输入..."></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getUserList()">查询</el-button>
                    <el-button type="primary" @click="openDialogInsert()">新增</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="records" border style="width: 99%">
                <el-table-column prop="uId" label="编号" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="avatar" label="头像" width="200" header-align="center" align="center">
                    <template slot-scope="scope">
                    <img :src="scope.row.avatar" alt="" style="width: 130px; height: 130px;">
                  </template>
                </el-table-column>
                <el-table-column prop="username" label="用户名" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="name" label="姓名" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="gender" label="性别" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="role" label="身份" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column label="操作" width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" circle size="small"
                            @click="openDialogUpdate(scope.row)"></el-button>
                        <el-button type="danger" icon="el-icon-delete" circle size="small"
                            @click="deleteUser(scope.row.uId)"></el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination @size-change="sizeChangeHandle" @current-change="currentChangeHandle"
                :current-page="pageIndex" :page-sizes="[5, 10, 20, 50, 100]" :page-size="pageSize" :total="total"
                layout="total, sizes, prev, pager, next, jumper" style="margin-top:30px;">
            </el-pagination>
        </el-card>

        <!-- 弹框 -->
        <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="80px" :model="form">
                <el-form-item label="头像">
                    <el-upload class="avatar-uploader" action="http://localhost:8080/upload" :headers="header"       
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.password"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="form.gender">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                        <el-radio label="保密"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="身份">
                    <el-radio-group v-model="form.role">
                        <el-radio label="USER"></el-radio>
                        <el-radio label="ADMIN"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-button type="success" @click="saveOrUpdate()">{{ title }}</el-button>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import { getUserList, updateUser, insertUser, deleteUser } from '@/api/user'

export default {
    name: 'CutomerList',
    data() {
        return {
            records: [],
            avatarUrl: 'https://yxy-live-share.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20240419210622.jpg',
            pageIndex: 1,
            pageSize: 5,
            total: 0,
            dialogFormVisible: false,
            title: '新增用户',
            form: {
                uId: '',
                username: '',
                password: '',
                gender: '',
                role: '',
                avatar:''
            },
            imageUrl: '',
            dialogImageUrl: '',
            header:{
                token:localStorage.getItem('token')
            }
        }
    },
    mounted() {
        this.getUserList()
    },
    methods: {
        handleAvatarSuccess(res, file) {
            this.form.avatar = res.data;
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        getUserList() {
            const params = {
                pageNum: this.pageIndex,
                pageSize: this.pageSize
            }
            getUserList({ params }).then(res => {
                this.records = res.data.records
                // 如果图片不存在使用默认路径图片
                this.records.forEach(record => {
                    if (record.avatar === null) {
                        record.avatar = this.avatarUrl
                    }
                })
                // 获取总数据
                this.total = res.data.total
            })
        },
        openDialogUpdate(record) {
            // 打开窗口
            this.dialogFormVisible = true;
            this.title = '修改用户'
            this.form = {
                ...record
            }
        },
        updateUser() {
            const data = {
                ...this.form
            }
            // 发送修改用户请求
            updateUser(data).then(res => {
                console.log(res);
                if (res.code === 200) {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
                    this.getUserList()
                    this.dialogFormVisible = false
                } else {
                    this.$message({
                        message: '修改失败',
                        type: 'error'
                    })
                }
            })
        },
        openDialogInsert() {
            // 打开窗口
            this.dialogFormVisible = true;
        },
        saveOrUpdate() {
            if (this.form.uId) {
                this.updateUser()
            } else {
                this.insertUser()
            }
        },
        // 新增用户
        insertUser() {
            // 打开窗口
            this.dialogFormVisible = true;

            // 发送请求
            insertUser(this.form).then(res => {
                if (res.code === 200) {
                    this.$message({
                        message: '添加成功',
                        type: 'success'
                    })
                    this.getUserList()
                    this.dialogFormVisible = false
                } else {
                    this.$message({
                        message: '添加失败',
                        type: 'error'
                    })
                }

            })
        },
        // 每页数
        sizeChangeHandle(val) {
            this.pageSize = val;
            this.getUserList();
        },
        // 当前页
        currentChangeHandle(val) {
            this.pageIndex = val;
            this.getUserList();
        },
        // 删除用户
        deleteUser(id) {
            this.$confirm('确定要删除该用户吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                console.log(id);
                deleteUser(id).then(res => {
                    if (res.data.code === 200) {
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        })
                        this.getUserList()
                    } else {
                        this.$message({
                            message: '删除失败',
                            type: 'error'
                        })
                    }
                })
            })
        },
    }
}
</script>


<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
    border: 1px dashed #8c939d;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>