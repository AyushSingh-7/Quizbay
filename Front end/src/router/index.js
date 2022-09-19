import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Screener from '@/components/Screener'
import OneQuestion from '@/components/OneQuestion'
import test from '@/components/test'
import framingQuestions from'@/components/framingQuestion'
import dynamicQuiz from '@/components/dynamicQuiz'
import YourContests from '@/components/YourContests'
import Leaderboard from '@/components/leaderboard'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/screener',
      name: 'Screener',
      component: Screener
    },
    {
      path: '/OneQuestion',
      name: 'OneQuestion',
      component: OneQuestion
    },
    {
      path: '/test',
      name: 'test',
      component: test
    }
    ,
    {
      path: '/framingQuestions',
      name: 'framingQuestions',
      component: framingQuestions
    },
    {
      path: '/dynamicquiz',
      name: 'dynamicQuiz',
      component: dynamicQuiz
    },
    {
      path: '/yourcontests',
      name: 'YourContests',
      component: YourContests
    
    },
    {
      path: '/leaderboard',
      name: 'Leaderboard',
      component: Leaderboard
    }
  ] 
}

)