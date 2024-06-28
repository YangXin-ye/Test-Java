<template>
    <div>
        <el-card>

            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="搜索信息">
                    <el-input placeholder="请输入..."></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getProductList()">查询</el-button>
                    <el-button type="primary" @click="openDialogInsert()">新增</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="records" border style="width: 99%">
                <el-table-column prop="id" label="编号" width="180" header-align="center" align="center" ref="idList">
                </el-table-column>
                <el-table-column prop="image" label="商品图片" width="200" header-align="center" align="center">
                    <template slot-scope="scope">
                    <img :src="scope.row.image" alt="" style="width: 130px; height: 130px;">
                  </template>
                </el-table-column>
                <el-table-column prop="name" label="商品名称" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="rating" label="商品评分" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="favorites" label="商品收藏量" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="price" label="商品价格" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="sales" label="商品销量" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="inventory" label="商品库存" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="categoryname" label="商品分类" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column label="操作" width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        <el-button type="primary" round size="small" @click="openDialogUpdate(scope.row)">编辑</el-button>
                        <el-button type="danger" round size="small" @click="deleteProduct(scope.row.id)">删除</el-button>
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
                <el-form-item label="商品图片">
                    <el-upload class="avatar-uploader" action="http://localhost:8080/upload" :headers="header"       
                        :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
                <el-form-item label="商品名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="商品价格">
                    <el-input v-model="form.price"></el-input>
                </el-form-item>
                <el-form-item label="商品库存">
                    <el-input v-model="form.inventory"></el-input>
                </el-form-item>
                <el-form-item label="商品分类">
                    <el-tag style="margin-right: 5px;" :key="tag" v-for="tag in form.dynamicTags" closable
                        :disable-transitions="false" @close="handleClose(tag)">
                        {{ tag.categoryname }}
                    </el-tag>
                    <el-input v-if="form.inputVisible" v-model="form.inputValue" ref="saveTagInput" size="small"
                        @keyup.enter.native="handleInputConfirm" @blur="handleInputConfirm"
                        @input="() => { this.$forceUpdate() }">
                    </el-input>
                    <el-button v-else size="small" @click="showInput">+ New Tag</el-button>
                </el-form-item>
                <el-button type="success" @click="saveOrUpdate()">{{ title }}</el-button>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import { getProductList, updateProduct, addProduct, deleteProduct, getTagList } from '@/api/product'

export default {
    name: 'Product',
    data() {
        return {
            records: [],
            imgUrl: 'https://yxy-live-share.oss-cn-beijing.aliyuncs.com/28b448a24017efe4302614f1f329e19b.png',
            pageIndex: 1,
            pageSize: 5,
            total: 0,
            dialogFormVisible: false,
            title: '新增商品',
            option: [],
            form: {
                id: '',
                image: '',
                name: '',
                price: '',
                inventory: '',
                dynamicTags: [],
                inputVisible: false,
                inputValue: ''
            },
            imageUrl: '',
            header:{
                token:localStorage.getItem('token')
            }
        }
    },
    mounted() {
        this.getProductList()
    },
    methods: {
        handleAvatarSuccess(res, file) {
            this.form.image = res.data;
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
        getProductList() {
            const params = {
                pageNum: this.pageIndex,
                pageSize: this.pageSize
            }
            getProductList({ params }).then(res => {
                this.records = res.data.records
                // 如果图片不存在使用默认路径图片
                this.records.forEach(record => {
                    if (record.image === null) {
                        record.image = this.imgUrl
                    }
                })
                // 获取总数据
                this.total = res.data.total

            })
        },
        openDialogUpdate(record) {
            // 打开窗口
            this.dialogFormVisible = true;
            this.title = '修改数据'
            this.form = {
                ...record
            }
            this.getTags()
        },
        updateProduct() {
            const data = {
                ...this.form
            }
            // 发送修改用户请求
            updateProduct(data).then(res => {
                if (res.code === 200) {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
                    this.getProductList()
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
            this.form = {}
            // 打开窗口
            this.dialogFormVisible = true;
            this.getTags()

        },
        saveOrUpdate() {
            if (this.form.id) {
                this.updateProduct()
            } else {
                this.insertProduct()
            }
        },
        // 新增商品
        insertProduct() {
            // 打开窗口
            this.dialogFormVisible = true;

            
            console.log(this.form);
            // 发送请求
            addProduct(this.form).then(res => {
                console.log(res);
                if (res.code === 200) {
                    this.$message({
                        message: '添加成功',
                        type: 'success'
                    })
                    // if (this.form.image === null) {
                    //     this.form.image = this.imgUrl
                    // }
                    this.getProductList()
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
            this.getProductList();
        },
        // 当前页
        currentChangeHandle(val) {
            this.pageIndex = val;
            this.getProductList();
        },
        // 删除商品
        deleteProduct(id) {
            this.$confirm('确定要删除该用户吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                deleteProduct(id).then(res => {
                    if (res.data.code === 200) {
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        })
                        this.getProductList()
                    } else {
                        this.$message({
                            message: '删除失败',
                            type: 'error'
                        })
                    }
                })
            })
        },
        getTags() {
            getTagList().then(res => {
                // console.log(res.data);
                const tags = res.data
                // 过滤名字
                const names = tags.filter(item => item.name !== '')
                this.form.dynamicTags = names
                this.$forceUpdate()
            })
        },
        handleClose(tag) {
            this.form.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
        },

        showInput() {
            this.form.inputVisible = true;
            this.$forceUpdate();

            this.$nextTick(() => {
                this.form.inputValue = '';
                this.$refs.saveTagInput.$refs.input.focus();
            });
        },

        handleInputConfirm() {
            let inputValue = this.form.inputValue;
            if (inputValue) {
                this.form.dynamicTags.push(inputValue);
            }
            this.form.inputVisible = false;
            this.form.inputValue = '';
        }
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