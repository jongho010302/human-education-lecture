Vue.component('feed-item', {
  props: ["feeds"],
  // 데이터와 마찬가지로 prop은 템플릿 내부에서 사용할 수 있으며
  template:  
  `<div>
    <div class="card mb-5" v-for="(feed, index) in feeds">
      <div class="card-header">
        <img :src="feed.profile_img" class="float-left" style="border-radius: 50%;" />
        <div class="pl-2 float-left">
          <h5 class="m-0">
            {{feed.profile_short}}
          </h5>
          <div class="h7 text-muted">
            {{feed.profile_name}}        
          </div>
        </div>
      </div>
      <div class="card-body">
        <div class="text-muted h7 mb-2">
          <i class="fa fa-clock-o"></i>
          <span>{{feed.times}}</span>
          <input type="hidden" class="hdn-times" v-model="feed.timestamp" />
        </div>
        <a href="#" class="card-link">
          <h5 class="card-title" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            {{feed.content}}
          </h5>
        </a>
        <p class="card-text">{{feed.content}}</p>
        <img :src="feed.attached_img" class="card-img-top" :class="[feed.attached_img == '' ? 'd-none' : '']" >
      </div>
      <div class="card-footer">
        <a href="#" class="card-link"><i class="fa fa-gittip"></i> Like</a>
        <a href="#" class="card-link"><i class="fa fa-comment"></i> Comment</a>
        <a href="#" class="card-link"><i class="fa fa-mail-forward"></i> Share</a>
      </div>
    </div>
  </div>
  `  
})

