 package com.veronicaohashi.studyblog.configuration

 import com.veronicaohashi.studyblog.domain.model.Author
 import com.veronicaohashi.studyblog.domain.model.Category
 import com.veronicaohashi.studyblog.domain.model.Post
 import com.veronicaohashi.studyblog.infrastrucure.repository.AuthorRepository
 import com.veronicaohashi.studyblog.infrastrucure.repository.CategoryRepository
 import com.veronicaohashi.studyblog.infrastrucure.repository.PostRepository
 import org.springframework.boot.CommandLineRunner
 import org.springframework.context.annotation.Configuration
 import java.util.UUID

 @Configuration
 class DataLoaderConfiguration(
     private val categoryRepository: CategoryRepository,
     private val authorRepository: AuthorRepository,
     private val postRepository: PostRepository
 ) : CommandLineRunner {

  override fun run(vararg args: String?) {
    loadCategory()
    loadAuthor()
    loadPost()
  }

  private fun loadCategory() {
    if (categoryRepository.count() == 0L) {
      listOf(
          Category(
              id = UUID.fromString("edf6c106-770e-412e-bbde-30eecf9ea4e2"),
              name = "Technology"
          ),
          Category(
              id = UUID.fromString("edf6c106-770e-412e-bbde-30eecf9ea4e2"),
              name = "Design"
          ),
      ).also { categoryRepository.saveAll(it) }
    }
  }

  private fun loadAuthor() {
    if (authorRepository.count() == 0L) {
      listOf(
          Author(
              id = UUID.fromString("80c18ae9-c9ef-4a92-a513-65fe487180e7"),
              name = "First Author",
              about = "This is my about"
          ),
          Author(
              id = UUID.fromString("299adaa3-f1f3-4e06-b00d-8e1909920e86"),
              name = "Veronica",
              about = "This is my veronica's about"
          )
      ).also { authorRepository.saveAll(it) }
    }
  }

   private fun loadPost() {
     if (postRepository.count() == 0L) {
       val authors = authorRepository.findAll()
       val categories = categoryRepository.findAll()
       listOf(
           Post(
               id = UUID.randomUUID(),
               title = "Data Loader Post",
               subtitle = "This is a post created on data loader",
               content = "No content for now",
               author = authors[1],
               categories = listOf(categories[0])
           ),
           Post(
               id = UUID.randomUUID(),
               title = "Second Post",
               subtitle = "This post will be used for a test",
               content = "No content for now",
               author = authors[1],
               categories = listOf(categories[1])
           )
       ).also { postRepository.saveAll(it) }
     }
   }
 }
