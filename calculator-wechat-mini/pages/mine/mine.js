const util = require('../../utils/util.js');
var app = getApp();
  
Component({
  data: {
    dailyFreeParseNum: '--',
    totalParseNum: '--',
    userInfo: null,
    hasUserInfo: false,
  },  
  /**
   * 组件的方法列表
   */
  methods: {
    onLoad: function() {},
    onShow: function() {
      if (!app.checkIsLogin()) {
        this.setData({
          hasUserInfo: false,
        })
      }
      if (app.globalData.hasUserInfo) {
        this.setData({
            userInfo: app.globalData.userInfo,
            hasUserInfo: app.globalData.hasUserInfo,
        })
      }
        // 获取每日剩余免费解析次数
        this.getDailyFreeParseNum(),
        // 获取当前用户总解析次数
        this.getTotalParseNum();
    },

    testTap() {
      wx.showToast({
        title: 'xxxxxxx',
        icon: 'none'
      })
    },

    /**
     * 授权登录
     */
    getUserInfo(e) {
      if (e.detail.errMsg !== 'getUserInfo:ok') {
        wx.showToast({
          title: '未授权，登录失败',
          icon: 'none'
        })
        return false;
      }
      wx.showLoading({
        title: "正在登录",
        mask: true
      });
      // 执行登录
      app.getUserInfo(res => {
        this.setData({
          userInfo: app.globalData.userInfo,
          hasUserInfo: app.globalData.hasUserInfo,
        })
        wx.hideLoading();
      })
    },

    //打赏
    showQrcode() {
      wx.previewImage({
        urls: ['https://m1-1253159997.image.myqcloud.com/images/f58330a41a41d8776db5a7860eb2c9b5.JPG'],
        current: 'https://m1-1253159997.image.myqcloud.com/images/f58330a41a41d8776db5a7860eb2c9b5.JPG' // 当前显示图片的http链接
      })
    },

    //分享小程序
    onShareAppMessage: function() {
      return {
        title: this.data.config_base_list.share_title ? this.data.config_base_list.share_title : '推荐一款超好用的视频去水印工具，免费解析不限次，大家都在用',
        path: '/pages/index/index',
        imageUrl: this.data.config_base_list.share_imageUrl ? this.data.config_base_list.share_imageUrl : '/images/share.jpg',
        success: function(e) {
          wx.showToast({
            title: "分享成功",
            icon: "success",
            duration: 2e3
          });
        },
        fail: function(e) {
          wx.showToast({
            title: "分享失败",
            icon: "none",
            duration: 2e3
          });
        }
      }
    },
  }
})