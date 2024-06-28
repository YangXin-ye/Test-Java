<template>
    <div>
        <el-card>

            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="搜索信息">
                    <el-input placeholder="请输入..."></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getCategoriesList()">查询</el-button>
                    <el-button type="primary" @click="openDialogInsert()">新增</el-button>
                </el-form-item>
            </el-form>

            <el-table :data="records" border style="width: 99%">
                <el-table-column prop="id" label="编号" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="categoryname" label="商品名称" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column prop="season" label="所属季节" width="180" header-align="center" align="center">
                </el-table-column>
                <el-table-column label="操作" width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        <el-button type="primary" round size="small" @click="openDialogUpdate(scope.row)">编辑</el-button>
                        <el-button type="danger" round size="small" @click="deleteCategories(scope.row.id)">删除</el-button>
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
                <el-form-item label="分类名称">
                    <el-input v-model="form.categoryname"></el-input>
                </el-form-item>
                <el-form-item label="所属季节">
                    <el-input v-model="form.season"></el-input>
                </el-form-item>
                <el-button type="success" @click="saveOrUpdate()">{{ title }}</el-button>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import { getCategoriesList, deleteCategories, updateCategories, insertCategories } from '@/api/categories'
export default {
    name: 'Categories',
    data() {
        return {
            records: [],
            pageIndex: 1,
            pageSize: 5,
            total: 0,
            dialogFormVisible: false,
            title: '新增分类',
            form: {
                id: '',
                categoryname: '',
                season: ''
            }
        }
    },
    mounted() {
        this.getCategoriesList()
    },
    methods: {
        getCategoriesList() {
            const params = {
                pageNum: this.pageIndex,
                pageSize: this.pageSize
            }
            getCategoriesList({ params }).then(res => {
                console.log(res.data);
                this.records = res.data.records
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
        },
        updateCategories() {
            const data = {
                ...this.form
            }
            // 发送修改用户请求
            updateCategories(data).then(res => {
                if (res.code === 200) {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
                    this.getCategoriesList()
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
            if (this.form.id) {
                this.updateCategories()
            } else {
                this.insertCategories()
            }
        },
        // 新增商品
        insertCategories() {
            // 打开窗口
            this.dialogFormVisible = true;

            // 发送请求
            insertCategories(this.form).then(res => {
                if (res.code === 200) {
                    this.$message({
                        message: '添加成功',
                        type: 'success'
                    })
                    this.getCategoriesList()
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
            this.getCategoriesList();
        },
        // 当前页
        currentChangeHandle(val) {
            this.pageIndex = val;
            this.getCategoriesList();
        },
        // 删除用户
        deleteCategories(id) {
            this.$confirm('确定要删除该用户吗?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                deleteCategories(id).then(res => {
                    if (res.code === 200) {
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        })
                        this.getCategoriesList()
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

<style scoped></style>