package com.agoda.sample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.pressImeActionButton
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import com.agoda.sample.screen.TestActivityScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(TestActivity::class.java)

    val screen = TestActivityScreen()

    @Test
    fun failingTestWithKakao() {
        screen {
            editText {
                typeText("Test input")
                pressImeAction()
            }
        }
    }

    @Test
    fun succeedingTestWithEspresso() {
        onView(withId(R.id.edit_text)).perform(typeText("No such item"), pressImeActionButton())
    }

    @Test
    fun testContentScreen() {
        screen {
            content {
                isVisible()
            }

            textViewLarge {
                click()
                isVisible()
                hasAnyText()
            }

            textViewSmall {
                isVisible()
                hasAnyText()
            }

            map {
                click()
                hasAnyTag("test_tag", "non_test_tag")
            }

            button {
                hasText("BUTTON")
            }

            ratingbar {
                hasRating(0f)
                setRatingAt(3f)
                hasRating(3f)
            }

            snackbarButton {
                click()
            }

            snackbar {
                isDisplayed()

                text { hasText("This is snackbar!") }
                action {
                    hasText("DISMISS")
                    click()
                    idle(500)
                }

                doesNotExist()
            }

            seekbar {
                hasProgress(70)
                dragProgressTo(30)
                hasProgress(30)
            }
        }
    }
}
