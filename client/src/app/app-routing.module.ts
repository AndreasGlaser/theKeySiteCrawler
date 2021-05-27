import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BlogsComponent} from "./blogs/blogs.component";
import {BlogComponent} from "./blog/blog.component";

const routes: Routes = [
  {path: "blogs", component: BlogsComponent},
  {path: "blog", component: BlogComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
